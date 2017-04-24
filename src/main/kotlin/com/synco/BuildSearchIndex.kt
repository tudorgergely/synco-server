package com.synco

import org.hibernate.search.jpa.Search
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Component
class BuildSearchIndex : ApplicationListener<ApplicationReadyEvent> {

    @PersistenceContext
    private val entityManager: EntityManager? = null

    /**
     * Create an initial Lucene index for the data already present in the
     * database.
     * This method is called when Spring's startup.
     */
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        try {
            val fullTextEntityManager = Search.getFullTextEntityManager(entityManager)
            fullTextEntityManager.createIndexer().startAndWait()
        } catch (e: InterruptedException) {
            println("An error occurred trying to build the search index: " + e.toString())
        }
    }

}