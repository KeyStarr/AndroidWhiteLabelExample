package com.example.whitelabelexample.domain.usecase

import com.example.whitelabelexample.domain.config.EnterUserIdConfig


class ValidateUserIdUseCase(
    configRep: EnterUserIdConfig
) {

    private val regex by lazy {
        configRep.inputRegex()?.let { Regex(it) }
    }

    operator fun invoke(input: String) = regex?.let { input.matches(it) } ?: true
}
