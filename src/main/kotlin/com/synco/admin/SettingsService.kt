package com.synco.admin

import com.synco.domain.Location

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
interface SettingsService {
    /**
     *
     */
    fun addLocation(location: Location)

    /**
     *
     */
    fun addEncryptedLocation(location: Location)
}