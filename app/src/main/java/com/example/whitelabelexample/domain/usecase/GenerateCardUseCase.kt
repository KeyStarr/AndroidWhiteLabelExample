package com.example.whitelabelexample.domain.usecase

import com.example.whitelabelexample.domain.repositories.net.CardNetRepository
import com.example.whitelabelexample.domain.repositories.storage.CardStorageRepository

class GenerateCardUseCase(
    private val cardNetRep: CardNetRepository,
    private val cardStorageRep: CardStorageRepository
) {

    operator fun invoke(fieldsMap: Map<String, String>) {
        val card = cardNetRep.generateCard(fieldsMap)
        cardStorageRep.save(card)
    }
}
