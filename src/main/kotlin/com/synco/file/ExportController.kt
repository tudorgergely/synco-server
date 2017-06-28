package com.synco.file

import com.synco.activity.ActivityRepository
import com.synco.domain.Activity
import com.synco.domain.LocalLocation
import org.springframework.web.bind.annotation.*
import java.io.FileInputStream
import java.util.*
import javax.servlet.http.HttpServletResponse

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@RestController
@RequestMapping("/api/export")
class ExportController(val locationRepository: LocalLocationRepository, val activityRepository: ActivityRepository) {
    @GetMapping("/{id}")
    @CrossOrigin
    fun downloadFile(@PathVariable id: Long, response: HttpServletResponse) {
        val location: LocalLocation = locationRepository.findOne(id).get()

        activityRepository.save(Activity(type = "download", content = location.fileMetadata.name!!, date = Date(), locations = arrayListOf("hdd")))

        response.contentType = "application/x-msdownload"
        response.setHeader("Content-disposition", "attachment; filename=" + location.fileMetadata.name)
        FileInputStream(location.path).copyTo(response.outputStream)
        response.flushBuffer()
    }
}