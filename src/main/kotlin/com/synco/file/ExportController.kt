package com.synco.file

import com.synco.domain.LocalLocation
import org.springframework.web.bind.annotation.*
import java.io.FileInputStream
import javax.servlet.http.HttpServletResponse

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@RestController
@RequestMapping("/api/export")
class ExportController(val locationRepository: LocalLocationRepository) {
    @GetMapping("/{id}")
    @CrossOrigin
    fun downloadFile(@PathVariable id: Long, response: HttpServletResponse) {
        val location: LocalLocation = locationRepository.findOne(id).get()
        response.contentType = "application/x-msdownload"
        response.setHeader("Content-disposition", "attachment; filename=" + location.fileMetadata.name)
        FileInputStream(location.path).copyTo(response.outputStream)
        response.flushBuffer()
    }
}