package com.synco.index

import com.synco.domain.File

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
interface IndexService {
    fun indexFile(file: File)
}