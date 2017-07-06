package com.synco.admin

import com.synco.domain.BackupLocation
import com.synco.domain.GoogleToken
import com.synco.domain.SyncLocation
import com.synco.domain.SyncLocationChangeEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class SettingsServiceImpl(val backupLocationRepository: BackupLocationRepository,
                          val syncLocationRepository: SyncLocationRepository,
                          val applicationEventPublisher: ApplicationEventPublisher,
                          val googleTokenRepository: GoogleTokenRepository) : SettingsService {
    override fun getGoogleToken(): GoogleToken? {
        return googleTokenRepository.findAll().firstOrNull()
    }

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

    override fun addGoogleToken(token: String, userId: String) {
        val currentToken = googleTokenRepository.findByUserId(userId)

        if (currentToken == null) {
            googleTokenRepository.deleteAll()
            googleTokenRepository.save(GoogleToken(token = token, userId = userId))
        } else {
            currentToken.token = token
            googleTokenRepository.save(currentToken)
        }
    }
}