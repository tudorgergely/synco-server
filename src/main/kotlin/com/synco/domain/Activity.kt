package com.synco.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Entity
class Activity(
        @Id
        @GeneratedValue
        var id: Long? = null,

        var date: Date,
        var type: String,
        var locations: List<String>,
        var content: String) {
}