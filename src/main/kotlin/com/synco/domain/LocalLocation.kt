package com.synco.domain

import org.hibernate.search.annotations.Indexed
import org.hibernate.search.annotations.IndexedEmbedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Entity
@Indexed
class LocalLocation(
        @Id
        @GeneratedValue
        var id: Long = 0L,
        var type: LocationType = LocationType.LOCAL,
        var payload: String? = null,
        @IndexedEmbedded
        var fileMetadata: FileMetadata = FileMetadata("", 0.0F)
)