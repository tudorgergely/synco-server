package com.synco.search

import com.synco.file.IndexService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.apache.lucene.search.ScoreDoc
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.index.DirectoryReader



/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Controller
@RequestMapping("/api/search")
class SearchController(val indexService: IndexService) {
    @PostMapping("/")
    fun search(q: String) = indexService.searchString("path", q)
}