package com.example.whitelabelexample.domain.usecase

import com.example.whitelabelexample.domain.repositories.net.CardNetRepository
import com.example.whitelabelexample.domain.repositories.storage.IsCardObtainedStorageRepository

class GenerateCardUseCase(
    private val cardNetRep: CardNetRepository,
    private val isCardObtainedStorageRep: IsCardObtainedStorageRepository
) {

    operator fun invoke(fieldsMap: Map<String, String>) {
        cardNetRep.generateCard(fieldsMap)
        isCardObtainedStorageRep.set(true)
    }
}
