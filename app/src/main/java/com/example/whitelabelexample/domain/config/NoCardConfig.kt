package com.example.whitelabelexample.domain.config

import com.example.whitelabelexample.domain.models.UserIdType
import com.example.whitelabelexample.domain.models.ObtainCardMethod

interface NoCardConfig {

    fun userIdType(): UserIdType

    fun userIdMask(): String?

    fun obtainCardMethods(): List<ObtainCardMethod>
}
