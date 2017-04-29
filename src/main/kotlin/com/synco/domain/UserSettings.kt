package com.synco.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Entity
data class UserSettings(
        @Id
        @GeneratedValue
        var id: Long,

        var localBackupLocations: List<String>,
        var localWatchLocations: List<String>
)