package com.synco.file

import com.synco.activity.ActivityRepository
import com.synco.backup.BackupService
import com.synco.domain.*
import org.apache.tika.Tika
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*
import java.io.FileOutputStream


@Service
class ImportServiceImpl(val localBackupService: BackupService<LocalLocation>,
                        val repository: LocalLocationRepository,
                        val googleRepo: GoogleLocationRepository,
                        val activityRepository: ActivityRepository,
                        val googleBackupService: BackupService<GoogleDriveLocation>) : ImportService {
    override fun importFile(file: MultipartFile) {
        importFile(convert(file))
    }

    override fun importFile(file: java.io.File) {
        val content = Tika().parseToString(file.inputStream()).replace("[^A-Za-z]+".toRegex(), " ")

        val bytes = ByteArrayOutputStream()
        file.inputStream().copyTo(bytes)

        val syncoFile = File(FileMetadata(file.name, file.length().toFloat(), content), ByteArrayInputStream(bytes.toByteArray()))
        val gSyncoFile = File(FileMetadata(file.name, file.length().toFloat(), content), ByteArrayInputStream(bytes.toByteArray()))
        val location = localBackupService.save(syncoFile);
        val googleLocation = googleBackupService.save(gSyncoFile)

        activityRepository.save(Activity(type = "upload", content = file.name!!, date = Date(), locations = arrayListOf("hdd", "google")))

        repository.save(location)
        googleRepo.save(googleLocation)
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