package com.synco.domain

import org.apache.lucene.analysis.core.LowerCaseFilterFactory
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilterFactory
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory
import org.apache.lucene.analysis.standard.StandardTokenizerFactory
import org.hibernate.search.annotations.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Entity
@Indexed
@AnalyzerDef(
        name = "edgeNGramAnalyzer",
        tokenizer = TokenizerDef(factory = StandardTokenizerFactory::class),
        filters = arrayOf(
                TokenFilterDef(factory = WordDelimiterFilterFactory::class),
                TokenFilterDef(factory = LowerCaseFilterFactory::class),
                TokenFilterDef(
                        factory = SnowballPorterFilterFactory::class,
                        params = arrayOf(
                                Parameter(name = "language", value = "English")
                        )
                ),
                TokenFilterDef(
                        factory = EdgeNGramFilterFactory::class,
                        params = arrayOf(
                                Parameter(name = "minGramSize", value = "3"),
                                Parameter(name = "maxGramSize", value = "50")
                        )
                )
        )
)
class LocalLocation(
        @Id
        @GeneratedValue
        var id: Long = 0L,
        var type: LocationType = LocationType.LOCAL,
        var path: String? = null,
        @IndexedEmbedded
        var fileMetadata: FileMetadata = FileMetadata("", 0.0F)
)