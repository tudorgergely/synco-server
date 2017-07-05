package com.synco.backup

import com.synco.admin.SettingsService
import com.synco.domain.File
import com.synco.domain.LocalLocation
import com.synco.exceptions.LocationNotFoundException
import org.springframework.stereotype.Service
import java.io.*


@Service
class LocalBackupServiceImpl(val settingsService: SettingsService) : BackupService<LocalLocation> {
    override fun retrieve(location: LocalLocation): File {
        val path = location.path;
        try {
            return File(location.fileMetadata, FileInputStream(path))
        } catch (e: FileNotFoundException) {
            throw LocationNotFoundException("Local location $path was not found on this system")
        }
    }

    override fun save(file: File): LocalLocation {
        var path = settingsService.getBackupLocation()?.path;
        if (path == null) {
            path = System.getProperty("user.home") + java.io.File.separator + "synco_files"
        }
//        System.getProperty("user.home") + java.io.File.separator + "synco_files"
//        if (path != null) {
        val customDir = java.io.File(path)
        val filePath = customDir.absolutePath + java.io.File.separator + file.metadata.size + "_" + file.metadata.name;
        if (customDir.exists() || customDir.mkdirs()) {
            FileOutputStream(filePath).use { outputStream -> file.inputStream.copyTo(outputStream) }
        }
        return LocalLocation(path = filePath, fileMetadata = file.metadata)
//        }

    }
}