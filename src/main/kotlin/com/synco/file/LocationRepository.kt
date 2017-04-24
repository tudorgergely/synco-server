package com.synco.file

import com.synco.domain.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@NoRepositoryBean
interface LocationRepository<T: Location<*>> : JpaRepository<T, Long>