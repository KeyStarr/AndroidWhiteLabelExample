package com.example.whitelabelexample.domain.config

import com.example.whitelabelexample.domain.models.UserIdType

interface UserIdConfig {

    fun type(): UserIdType

    fun mask(): String?

    fun regex(): String?
}