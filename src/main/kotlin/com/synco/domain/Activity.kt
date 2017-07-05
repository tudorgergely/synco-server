package com.synco.domain

import java.util.*
import javax.persistence.*

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Entity
class Activity(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @Column
        var date: Date,
        @Column
        var type: String,

        @Column
        @ElementCollection
        var locations: List<String>,
        @Column
        var content: String
)