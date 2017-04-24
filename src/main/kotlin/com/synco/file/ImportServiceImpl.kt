package com.synco.file

import com.synco.backup.BackupService
import com.synco.domain.File
import com.synco.domain.FileMetadata
import com.synco.domain.LocalLocation
import org.apache.tika.Tika
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream

@Service
class ImportServiceImpl(val localBackupService: BackupService<LocalLocation>, val repository: LocalLocationRepository) : ImportService {
    override fun importFile(file: MultipartFile) {
        val content = Tika().parseToString(file.inputStream)
        val location = localBackupService.save(File(FileMetadata(file.originalFilename, file.size.toFloat(), content), file.inputStream));

        repository.save(location)
    }
}