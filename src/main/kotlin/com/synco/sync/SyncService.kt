package com.synco.sync

import com.synco.domain.Location

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
interface SyncService {
    fun addLocation(location: Location)
}