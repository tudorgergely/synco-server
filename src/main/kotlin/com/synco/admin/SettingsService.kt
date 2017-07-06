package com.synco.admin

import com.synco.domain.BackupLocation
import com.synco.domain.GoogleToken
import com.synco.domain.Location
import com.synco.domain.SyncLocation

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
interface SettingsService {
    fun addBackupLocation(path: String): String

    fun getBackupLocation(): BackupLocation?

    fun addSyncLocation(path: String): String

    fun getSyncLocation(): SyncLocation?

    fun addGoogleToken(token: String, userId: String)

    fun getGoogleToken(): GoogleToken?
}