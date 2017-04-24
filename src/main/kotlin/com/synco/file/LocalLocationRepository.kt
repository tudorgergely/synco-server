package com.synco.file

import com.synco.domain.LocalLocation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Repository
interface LocalLocationRepository : JpaRepository<LocalLocation, Long>