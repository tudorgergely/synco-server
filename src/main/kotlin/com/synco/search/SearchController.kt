package com.synco.search

import com.synco.file.IndexService
import org.apache.http.entity.ContentType
import org.springframework.stereotype.Controller
import org.apache.lucene.search.ScoreDoc
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.index.DirectoryReader
import org.springframework.web.bind.annotation.*


/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Controller
@RequestMapping("/api/search")
class SearchController(val indexService: IndexService) {
    @PostMapping("/", consumes = arrayOf("application/json"))
    @ResponseBody
    @CrossOrigin
    fun search(@RequestBody q: Map<String, String>): MutableList<Any?>? {
        return indexService.searchString("name", q.get("q")!!)
    }
}