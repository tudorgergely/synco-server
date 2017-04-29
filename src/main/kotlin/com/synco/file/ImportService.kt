package com.synco.file

import com.synco.domain.File
import org.springframework.web.multipart.MultipartFile

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
interface ImportService {
    fun importFile(file: MultipartFile)

    fun importFile(file: java.io.File)
}