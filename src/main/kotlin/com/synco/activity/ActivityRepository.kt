package com.synco.activity

import com.synco.domain.Activity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Repository
interface ActivityRepository : JpaRepository<Activity, Long> {
    fun findFirst5ByTypeOrderByDateDesc(type: String): List<Activity>

    fun findFirst50ByOrderByDateDesc(): List<Activity>
}