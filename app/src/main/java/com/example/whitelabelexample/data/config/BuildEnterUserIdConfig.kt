package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.EnterUserIdConfig
import com.example.whitelabelexample.domain.models.UserIdType

class BuildEnterUserIdConfig : EnterUserIdConfig {

    override fun userIdType(): UserIdType = BuildConfig.USER_ID_TYPE

    override fun inputRegex(): String? = BuildConfig.USER_ID_REGEX

    override fun userIdInputMask(): String? = BuildConfig.USER_ID_MASK
}
