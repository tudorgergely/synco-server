package com.synco.file

import org.springframework.web.multipart.MultipartFile

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
interface ImportService {
    fun saveToDisk(file: MultipartFile)
}