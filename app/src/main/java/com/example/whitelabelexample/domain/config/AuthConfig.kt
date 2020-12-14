package com.example.whitelabelexample.domain.config

import com.example.whitelabelexample.domain.models.UserIdParams

interface AuthConfig {

    fun userIdParams(): UserIdParams
}
