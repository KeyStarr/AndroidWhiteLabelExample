package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.AuthConfig
import com.example.whitelabelexample.domain.models.UserIdType

class BuildAuthConfig : AuthConfig {

    override fun userIdType(): UserIdType = BuildConfig.USER_ID
}
