package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.AuthConfig
import com.example.whitelabelexample.domain.models.UserIdParams

class BuildAuthConfig : AuthConfig {

    override fun userIdParams() = UserIdParams(
        BuildConfig.USER_ID_TYPE,
        BuildConfig.USER_ID_MASK,
        BuildConfig.USER_ID_REGEX
    )
}
