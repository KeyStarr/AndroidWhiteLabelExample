package com.example.whitelabelexample.domain.usecase.auth

import com.example.whitelabelexample.domain.config.AuthConfig

class GetUserIdParamsUseCase(private val config: AuthConfig) {

    operator fun invoke() = config.userIdParams()
}
