package com.synco.backup

import com.synco.domain.File
import com.synco.domain.LocalLocation
import com.synco.exceptions.LocationNotFoundException
import org.springframework.stereotype.Service
import java.io.*


@Service
class LocalBackupServiceImpl : BackupService<LocalLocation> {
    override fun retrieve(location: LocalLocation): File {
        val path = location.payload;
        try {
            return File(location.fileMetadata,  FileInputStream(path))
        } catch (e: FileNotFoundException) {
            throw LocationNotFoundException("Local location $path was not found on this system")
        }
    }

    override fun save(file: File): LocalLocation {
        val path = System.getProperty("user.home") + java.io.File.separator + "synco_files"
        val customDir = java.io.File(path)
        val filePath = customDir.absolutePath + java.io.File.separator + file.metadata.name
        if (customDir.exists() || customDir.mkdirs()) {
            FileOutputStream(filePath).use { outputStream -> file.inputStream.copyTo(outputStream) }
        }
        return LocalLocation(payload = filePath, fileMetadata = file.metadata)
    }
}