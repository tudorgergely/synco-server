package com.synco.admin

import com.synco.domain.SyncLocation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Repository
interface SyncLocationRepository : JpaRepository<SyncLocation, Long>