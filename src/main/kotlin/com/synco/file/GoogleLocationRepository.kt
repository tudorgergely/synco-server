package com.synco.file

import com.synco.domain.GoogleDriveLocation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Repository
interface GoogleLocationRepository : JpaRepository<GoogleDriveLocation, Long> {
}