package com.example.whitelabelexample.domain.usecase

import com.example.whitelabelexample.domain.repositories.storage.CardStorageRepository

class HasCardUseCase(
    private val cardStorageRep: CardStorageRepository
) {

    operator fun invoke() = cardStorageRep.get() != null
}
