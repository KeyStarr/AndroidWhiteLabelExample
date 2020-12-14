package com.example.whitelabelexample.domain.usecase.auth

import com.example.whitelabelexample.domain.config.UserIdConfig

class ValidateUserIdUseCase(
    configRep: UserIdConfig
) {

    private val regex by lazy {
        configRep.regex()?.let { Regex(it) }
    }

    operator fun invoke(input: String) = regex?.let { input.matches(it) } ?: true
}
