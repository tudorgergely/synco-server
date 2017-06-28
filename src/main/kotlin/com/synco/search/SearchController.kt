package com.synco.search

import com.synco.activity.ActivityRepository
import com.synco.domain.Activity
import com.synco.file.IndexService
import org.apache.http.entity.ContentType
import org.springframework.stereotype.Controller
import org.apache.lucene.search.ScoreDoc
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.index.DirectoryReader
import org.springframework.web.bind.annotation.*
import java.util.*


/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Controller
@RequestMapping("/api/search")
class SearchController(val indexService: IndexService, val activityRepository: ActivityRepository) {
    @PostMapping("/", consumes = arrayOf("application/json"))
    @ResponseBody
    @CrossOrigin
    fun search(@RequestBody q: Map<String, String>): MutableList<Any?>? {
        activityRepository.save(Activity(type = "search", content = q.get("q")!!, date = Date(), locations = arrayListOf("google", "hdd")))

        return indexService.searchString("name", q.get("q")!!)
    }
}