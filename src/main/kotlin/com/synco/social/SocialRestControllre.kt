package com.synco.social

import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.lang.StringUtils
import org.springframework.http.MediaType
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.ConnectionRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.io.Serializable


/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@RestController
class SocialRestControllre(
        private val connectionFactoryLocator: ConnectionFactoryLocator,
        private val connectionRepository: ConnectionRepository) {
    @RequestMapping(produces = arrayOf(MediaType.APPLICATION_JSON_VALUE), method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun connect(): Set<ConnectionInfo> {
        val response: HashSet<ConnectionInfo> = HashSet()
        val registeredProviderIds = connectionFactoryLocator.registeredProviderIds()
        val connections = connectionRepository.findAllConnections()
        if (CollectionUtils.isNotEmpty(registeredProviderIds)) {
            for (providerId in registeredProviderIds) {
                val connectionInfo = ConnectionInfo(providerId, "Alias")
                val connectionList = connections.get(providerId)
                if (CollectionUtils.isNotEmpty(connectionList)) {
                    val displayName = connectionList!!.get(0).getDisplayName()
                    if (StringUtils.isNotBlank(displayName)) {
                        connectionInfo.displayName = displayName
                    }
                    connectionInfo.markAsConfigured()
                }

                response.add(connectionInfo);
            }
        }
        return response
    }
}

class ConnectionInfo : Serializable {

    var provider: String? = null
    var displayName = "No Name"
    var isConfigured: Boolean = false

    constructor() {}

    constructor(provider: String, displayName: String) {
        this.provider = provider
        this.displayName = displayName
    }

    fun markAsConfigured(): ConnectionInfo {
        this.isConfigured = true
        return this
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}

