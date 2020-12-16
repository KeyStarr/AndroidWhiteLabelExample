package com.example.whitelabelexample.domain.usecase.auth

import com.example.whitelabelexample.domain.config.AuthConfig

class ValidateUserIdUseCase(
    configRep: AuthConfig
) {

    private val regex by lazy {
        configRep.userIdType().regex?.let { Regex(it) }
    }

    operator fun invoke(input: String) = regex?.let { input.matches(it) } ?: true
}
