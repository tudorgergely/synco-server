package com.synco.flow

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds


/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Service
class FileWatcher {
    @Async
    fun watchFiles(path: String, callback: (file: File) -> Unit) {
        while (true) {
            val myDir = Paths.get(path)

            val watcher = myDir.fileSystem.newWatchService()
            myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY)

            val watckKey = watcher.take()

            val events = watckKey.pollEvents()
                for (event in events) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    val filePath = event.context() as Path
                    val file = File("$path/$filePath")

                    if (!file.isDirectory) {
                        println("Importing $file")
                        callback(file)
                    }
                }
            }
        }
    }
}