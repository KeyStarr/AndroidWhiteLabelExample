package com.example.whitelabelexample.domain.usecase.card

import com.example.whitelabelexample.domain.repositories.storage.IsCardObtainedStorageRepository

class HasCardUseCase(
    private val isCardObtainedStorageRep: IsCardObtainedStorageRepository
) {

    operator fun invoke() = isCardObtainedStorageRep.get() ?: false
}
