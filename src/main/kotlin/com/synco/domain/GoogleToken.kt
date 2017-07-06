package com.synco.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Entity
class GoogleToken (
        @Id
        @GeneratedValue
        var id: Long? = null,
        var token: String,
        var userId: String? = null,
        var folderId: String? = null
)