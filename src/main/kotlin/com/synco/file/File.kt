package com.synco.file

import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Entity
@Indexed
class   File(
        @Field val path: String
) {
    @Id @GeneratedValue var id: Long = 0L
}