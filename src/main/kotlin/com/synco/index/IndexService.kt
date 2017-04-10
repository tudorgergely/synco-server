package com.synco.index

import com.synco.file.File

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
interface IndexService {
    fun indexFile(file: File)
}