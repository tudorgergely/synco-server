package com.synco.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Entity
data class UserSettings(
        @Id
        @GeneratedValue
        var id: Long? = null,

        var localBackupLocations: ArrayList<String>,
        var localWatchLocations: ArrayList<String>,

        var googleToken: String,
        var dropboxToken: String
)