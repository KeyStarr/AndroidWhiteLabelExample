package com.example.whitelabelexample.domain.usecase.card

import com.example.whitelabelexample.domain.config.CardConfig
import com.example.whitelabelexample.domain.models.CardField
import java.lang.IllegalStateException

class GetCardFormFieldsUseCase(private val config: CardConfig) {

    operator fun invoke(): List<CardField> {
        val fields = config.virtualFormFields()
        if (fields.isEmpty()) {
            throw IllegalStateException("At least one card form field is required - fix config!")
        }
        return fields
    }
}