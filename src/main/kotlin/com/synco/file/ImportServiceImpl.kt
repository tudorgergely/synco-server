package com.synco.file

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class ImportServiceImpl(val indexService: IndexService, val repository: FileRepository) : ImportService {
    override fun saveToDisk(file: MultipartFile) {
        val path = System.getProperty("user.home") + File.separator + "synco_files"
        val customDir = File(path)
        val filePath = customDir.absolutePath + File.separator + file.originalFilename
        if (customDir.exists() || customDir.mkdirs()) {
            Files.copy(file.inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING)
        }
        repository.save(com.synco.file.File(filePath))
    }
}