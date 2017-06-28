package com.synco.settings

import com.synco.domain.UserSettings
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@RestController("/settings")
class SettingsController(var userSettingsRepo: UserSettingsRepo) {
    @PostMapping("/localBackup")
    fun addLocalBackupLocation(path: String) {
        val setting: UserSettings = userSettingsRepo.findById(0).get()
        setting.localBackupLocations.add(path)
    }

    @PostMapping("/localWatch")
    fun addLocalWatchLocation(path: String) {
        val setting: UserSettings = userSettingsRepo.findById(0).get()
        setting.localWatchLocations.add(path)
    }
}