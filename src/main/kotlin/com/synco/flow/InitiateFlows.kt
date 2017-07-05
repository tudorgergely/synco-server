package com.synco.flow

import com.synco.admin.SettingsService
import com.synco.admin.SyncLocationRepository
import com.synco.domain.SyncLocationChangeEvent
import com.synco.file.ImportService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Component
class InitiateFlows(val fileWatcher: FileWatcher,
                    val importService: ImportService,
                    val settingsService: SettingsService) : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent?) {
        initiateFlows()
    }

    @EventListener
    fun onSyncLocationChange(event: SyncLocationChangeEvent) {
        initiateFlows()
    }

    private fun initiateFlows() {
        val path = settingsService.getSyncLocation()?.path;
        if (path != null) {
            fileWatcher.watchFiles(path, callback = { file -> importService.importFile(file) })
        }
    }
}