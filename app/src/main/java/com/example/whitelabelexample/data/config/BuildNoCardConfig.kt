package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.NoCardConfig
import com.example.whitelabelexample.domain.models.ObtainCardMethod
import com.example.whitelabelexample.domain.models.UserIdType

class BuildNoCardConfig : NoCardConfig {

    override fun userIdType(): UserIdType = BuildConfig.USER_ID_TYPE

    override fun userIdMask(): String? = BuildConfig.USER_ID_MASK

    override fun obtainCardMethods(): List<ObtainCardMethod> = BuildConfig.OBTAIN_CARD_METHODS
}
