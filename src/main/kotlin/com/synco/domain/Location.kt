package com.synco.domain

import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed
import org.hibernate.search.annotations.IndexedEmbedded
import javax.persistence.Embedded
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**@@
 * @author Tudor Gergely, Catalysts GmbH
 */
@MappedSuperclass
open class Location<T>(
        @Id @GeneratedValue var id: Long = 0L,
        var type: LocationType = LocationType.LOCAL,
        open var payload: T? = null,
        @IndexedEmbedded open var fileMetadata: FileMetadata = FileMetadata("", 0.0F)
)
