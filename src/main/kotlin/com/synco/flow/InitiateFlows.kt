package com.synco.flow

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Component
class InitiateFlows(val fileWatcher: FileWatcher) : ApplicationListener<ApplicationReadyEvent>{
    override fun onApplicationEvent(event: ApplicationReadyEvent?) {
        initiateFlows()
    }

    private fun initiateFlows() {
        fileWatcher.watchFiles("/home/tudorgergely/synco_watch")
    }
}