package com.synco.file

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Controller
@RequestMapping("/api/import")
class ImportController(val importService: ImportService) {
    @PostMapping("/")
    @ResponseBody
    fun receiveFile(@RequestParam("file") file: MultipartFile?) {
        importService.saveToDisk(file!!)
    }
}