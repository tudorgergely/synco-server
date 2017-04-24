package com.synco.admin

import com.synco.domain.Location
import org.springframework.stereotype.Service

@Service
class SettingsServiceImpl : SettingsService {
    override fun addLocation(location: Location<*>) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addEncryptedLocation(location: Location<*>) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}