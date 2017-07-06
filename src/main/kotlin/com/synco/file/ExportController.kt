package com.synco.file

import com.synco.activity.ActivityRepository
import com.synco.admin.SettingsService
import com.synco.backup.BackupService
import com.synco.backup.GoogleDriveBackupServiceImpl
import com.synco.domain.Activity
import com.synco.domain.GoogleDriveLocation
import com.synco.domain.LocalLocation
import org.springframework.web.bind.annotation.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*
import javax.servlet.http.HttpServletResponse

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@RestController
@RequestMapping("/api/export")
class ExportController(val locationRepository: LocalLocationRepository,
                       val activityRepository: ActivityRepository,
                       val settingsService: SettingsService,
                       val googleLocationRepository: GoogleLocationRepository,
                       val googleDriveBackupService: BackupService<GoogleDriveLocation>) {
    @GetMapping("/{id}")
    @CrossOrigin
    fun downloadFile(@PathVariable id: Long, response: HttpServletResponse) {
        val location: LocalLocation = locationRepository.findById(id).get()

        activityRepository.save(Activity(type = "download", content = location.fileMetadata.name, date = Date(), locations = arrayListOf("hdd")))

        response.contentType = "application/x-msdownload"
        response.setHeader("Content-disposition", "attachment; filename=" + location.fileMetadata.name)
        FileInputStream(location.path).copyTo(response.outputStream)
        response.flushBuffer()
    }

    @GetMapping("/restoreGoogle")
    @CrossOrigin
    fun restoreFromGDrive() {
        googleLocationRepository.findAll()
                .filter { location ->
                    location.driveId != null
                }
                .map { location ->
                    googleDriveBackupService.retrieve(location)
                }
                .forEach {
                    file ->
                    var path = settingsService.getBackupLocation()?.path;
                    if (path == null) {
                        path = System.getProperty("user.home") + java.io.File.separator + "synco_files"
                    }

                    val customDir = java.io.File(path + java.io.File.separator + "google_restore")
                    val filePath = customDir.absolutePath + java.io.File.separator + file.metadata.size + "_" + file.metadata.name;
                    if (customDir.exists() || customDir.mkdirs()) {
                        FileOutputStream(filePath).use { outputStream -> file.inputStream.copyTo(outputStream) }
                    }
                }
    }
}