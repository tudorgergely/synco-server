package com.synco.backup

import com.synco.domain.Location
import com.synco.file.File

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
interface BackupService {
    fun save(location: Location, file: File)

    fun retrieve(location: Location)
}