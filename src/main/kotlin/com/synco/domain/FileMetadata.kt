package com.synco.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Fields
import org.hibernate.search.annotations.Indexed
import org.hibernate.search.annotations.Store
import javax.persistence.Embeddable
import javax.persistence.Transient

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Embeddable
data class FileMetadata(
        @Field
        var name: String = "",

        @Field
        var size: Float = 0.0F,

        @Field
        @JsonIgnore
        var content: String = ""
)