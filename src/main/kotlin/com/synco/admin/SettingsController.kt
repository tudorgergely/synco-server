package com.synco.admin

import com.synco.domain.BackupLocation
import com.synco.domain.SyncLocation
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@RequestMapping("/settings")
@Controller
class SettingsController(val settingsService: SettingsService) {
    @PostMapping("/backupPath")
    @ResponseBody
    @CrossOrigin
    fun setBackupPath(@RequestBody path: BackupLocation): String {
        return settingsService.addBackupLocation(path.path)
    }

    @PostMapping("/syncPath")
    @ResponseBody
    @CrossOrigin
    fun setSyncPath(@RequestBody path: SyncLocation): String {
        return settingsService.addSyncLocation(path.path)
    }

    @GetMapping("/backupPath")
    @ResponseBody
    @CrossOrigin
    fun getBackupPath(): String? {
        return settingsService.getBackupLocation()?.path
    }

    @GetMapping("/syncPath")
    @ResponseBody
    @CrossOrigin
    fun getSyncPath(): String? {
        return settingsService.getSyncLocation()?.path
    }
}