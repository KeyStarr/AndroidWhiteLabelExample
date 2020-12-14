package com.example.whitelabelexample.domain.usecase

import com.example.whitelabelexample.domain.repositories.net.CardNetRepository

class GenerateCardUseCase(private val cardNetRep: CardNetRepository) {

    operator fun invoke(fieldsMap: Map<String, String>) {
        cardNetRep.generateCard(fieldsMap)
    }
}
