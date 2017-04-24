package com.synco.file

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.web.multipart.MultipartHttpServletRequest



/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Controller
@RequestMapping("/api/import")
class ImportController(val importService: ImportService) {
    @PostMapping("/")
    @ResponseBody
    @CrossOrigin
    fun receiveFile(request: HttpServletRequest) {
        val multipartRequest = request as MultipartHttpServletRequest
        val fileMap = multipartRequest.fileMap
        fileMap.values.forEach { file -> importService.importFile(file) }
    }
}