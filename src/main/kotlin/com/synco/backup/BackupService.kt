package com.synco.backup

import com.synco.domain.Location
import com.synco.domain.File

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
interface BackupService<T> {
    fun save(file: File): T

    fun retrieve(location: T): File
}