package com.example.whitelabelexample.domain.config

import com.example.whitelabelexample.domain.models.UserIdType

interface EnterUserIdConfig {

    fun userIdType(): UserIdType

    fun inputRegex(): String?

    fun userIdInputMask(): String?
}
