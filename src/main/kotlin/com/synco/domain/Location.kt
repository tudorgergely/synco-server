package com.synco.domain

import org.hibernate.search.annotations.Field
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**@@
 * @author Tudor Gergely, Catalysts GmbH
 */
@Entity
class Location(
        @Field val path: String,
        @Field val type: LocationType
) {
    @Id @GeneratedValue var id: Long = 0L
}

enum class LocationType {
    LOCAL,
    GOOGLE_DRIVE
}
