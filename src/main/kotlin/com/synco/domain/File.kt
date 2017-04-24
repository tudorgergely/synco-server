package com.synco.domain

import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
data class File(
        val metadata: FileMetadata,
        val inputStream: InputStream
)