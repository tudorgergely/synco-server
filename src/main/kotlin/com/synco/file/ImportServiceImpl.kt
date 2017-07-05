package com.synco.file

import com.synco.activity.ActivityRepository
import com.synco.backup.BackupService
import com.synco.domain.Activity
import com.synco.domain.File
import com.synco.domain.FileMetadata
import com.synco.domain.LocalLocation
import org.apache.tika.Tika
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*
import java.io.FileOutputStream



@Service
class ImportServiceImpl(val localBackupService: BackupService<LocalLocation>, val repository: LocalLocationRepository,
                        val activityRepository: ActivityRepository) : ImportService {
    override fun importFile(file: MultipartFile) {
        importFile(convert(file))
    }

    override fun importFile(file: java.io.File) {
        val content = Tika().parseToString(file.inputStream()).replace("[^A-Za-z]+".toRegex(), " ")
        val location = localBackupService.save(File(FileMetadata(file.name, file.length().toFloat(), content), file.inputStream()));

        activityRepository.save(Activity(type = "upload", content = file.name!!, date = Date(), locations = arrayListOf("hdd", "google")))

        repository.save(location)
    }

    fun convert(file: MultipartFile): java.io.File {
        val convFile = java.io.File(file.originalFilename)
        convFile.createNewFile()
        val fos = FileOutputStream(convFile)
        fos.write(file.bytes)
        fos.close()
        return convFile
    }
}