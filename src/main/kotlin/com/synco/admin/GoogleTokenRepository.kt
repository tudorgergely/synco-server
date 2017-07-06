package com.synco.admin

import com.synco.domain.GoogleToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Repository
interface GoogleTokenRepository : JpaRepository<GoogleToken, Long> {
    fun findByUserId(userId: String): GoogleToken?
}