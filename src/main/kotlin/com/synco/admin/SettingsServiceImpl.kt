package com.synco.admin

import com.synco.domain.BackupLocation
import com.synco.domain.SyncLocation
import com.synco.domain.SyncLocationChangeEvent
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class SettingsServiceImpl(val backupLocationRepository: BackupLocationRepository,
                          val syncLocationRepository: SyncLocationRepository,
                          val applicationEventPublisher: ApplicationEventPublisher) : SettingsService {
    override fun getSyncLocation(): SyncLocation? {
        return syncLocationRepository.findAll().firstOrNull()
    }

    override fun getBackupLocation(): BackupLocation? {
        return backupLocationRepository.findAll().firstOrNull()
    }

    override fun addSyncLocation(path: String): String {
        val location = SyncLocation(path = path)

        syncLocationRepository.deleteAll()
        syncLocationRepository.save(location)

        applicationEventPublisher.publishEvent(SyncLocationChangeEvent(this))

        return path
    }

    override fun addBackupLocation(path: String): String {
        backupLocationRepository.deleteAll()
        backupLocationRepository.save(BackupLocation(path = path))


        return path;
    }
}