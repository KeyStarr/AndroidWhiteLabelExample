package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.UserIdConfig
import com.example.whitelabelexample.domain.models.UserIdType

class BuildUserIdConfig : UserIdConfig {

    override fun type(): UserIdType = BuildConfig.USER_ID_TYPE

    override fun mask() = BuildConfig.USER_ID_MASK

    override fun regex() = BuildConfig.USER_ID_REGEX
}
