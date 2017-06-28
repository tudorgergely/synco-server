package com.synco.settings

import com.synco.domain.UserSettings
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Repository
interface UserSettingsRepo : JpaRepository<UserSettings, Long>