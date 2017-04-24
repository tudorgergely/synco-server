package com.synco.file

import com.synco.domain.File
import com.synco.domain.LocalLocation
import com.synco.domain.Location
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.document.StringField
import org.apache.lucene.document.TextField
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
//import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.store.RAMDirectory
import org.springframework.stereotype.Service
import org.springframework.data.domain.ExampleMatcher.matching
import org.hibernate.loader.entity.plan.EntityLoader.forEntity
import org.hibernate.search.jpa.FullTextEntityManager
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.PersistenceContextType
import javax.transaction.Transactional


@Service
class IndexServiceImpl : IndexService {

    @PersistenceContext(type = PersistenceContextType.EXTENDED, name = "filesPU")
    private val entityManager: EntityManager? = null
    final val directory = RAMDirectory()
    final val analyzer = StandardAnalyzer()
    final val config = IndexWriterConfig(analyzer)
    final val writer = IndexWriter(directory, config)

    override fun indexString(name: String, value: String) {
        val doc = Document()
        doc.add(TextField(name, value, Field.Store.YES))
        writer.addDocument(doc)
        writer.commit()
    }

    @Transactional
    override fun searchString(name: String, q: String): MutableList<Any?>? {
        val fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager)
        val qb = fullTextEntityManager.searchFactory
                .buildQueryBuilder().forEntity(LocalLocation::class.java).get()
        val luceneQuery = qb
                .keyword()
                .onFields("fileMetadata.name")
                .matching(q)
                .createQuery()
        val jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, LocalLocation::class.java)
        return jpaQuery.resultList
    }
}