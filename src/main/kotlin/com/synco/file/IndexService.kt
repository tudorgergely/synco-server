package com.synco.file

import org.apache.lucene.document.Document

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
interface IndexService {
    fun indexString(name: String, value: String)

    fun searchString(name: String, q: String): MutableList<Any?>?
}