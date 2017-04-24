package com.synco.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.search.annotations.Analyzer
import org.hibernate.search.annotations.Field
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@Embeddable
data class FileMetadata(
        @Field
        @Analyzer(definition = "edgeNGramAnalyzer")
        var name: String = "",

        @Field
        var size: Float = 0.0F,

        @Field
        @JsonIgnore
        @Analyzer(definition = "edgeNGramAnalyzer")
        @Column(columnDefinition = "text")
        var content: String = ""
)