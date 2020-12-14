package com.example.whitelabelexample.domain.usecase.card

import com.example.whitelabelexample.domain.repositories.net.CardNetRepository
import com.example.whitelabelexample.domain.repositories.storage.IsCardObtainedStorageRepository

class BindCardUseCase(
    private val netRep: CardNetRepository,
    private val isCardObtainedStorageRep: IsCardObtainedStorageRepository
) {

    operator fun invoke(number: String) {
        netRep.bindPhysicalCard(number)
        isCardObtainedStorageRep.set(true)
    }
}
