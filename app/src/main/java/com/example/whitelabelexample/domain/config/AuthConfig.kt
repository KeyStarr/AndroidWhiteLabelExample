package com.example.whitelabelexample.domain.config

import com.example.whitelabelexample.domain.models.UserIdType

interface AuthConfig {

    fun userIdType(): UserIdType
}
