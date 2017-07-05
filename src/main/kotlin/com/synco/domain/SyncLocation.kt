package com.synco.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Entity
class SyncLocation(
        @Id
        @GeneratedValue
        val id: Long? = null,

        val path: String
)