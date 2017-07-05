package com.synco.admin

import com.synco.domain.BackupLocation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Repository
interface BackupLocationRepository: JpaRepository<BackupLocation, Long>