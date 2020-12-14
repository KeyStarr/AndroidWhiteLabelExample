package com.example.whitelabelexample.domain.usecase.card

import com.example.whitelabelexample.domain.config.CardConfig
import com.example.whitelabelexample.domain.models.ObtainCardMethod
import java.lang.IllegalStateException

class GetObtainMethodsUseCase(private val config: CardConfig) {

    operator fun invoke(): List<ObtainCardMethod> {
        val methods = config.obtainmentMethods()
        if (methods.isEmpty()) {
            throw IllegalStateException("At least one obtainment method is required - fix config!")
        }
        return methods
    }
}