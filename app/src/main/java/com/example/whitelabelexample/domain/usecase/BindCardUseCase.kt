package com.example.whitelabelexample.domain.usecase

import com.example.whitelabelexample.domain.repositories.net.CardNetRepository
import com.example.whitelabelexample.domain.repositories.storage.CardStorageRepository

class BindCardUseCase(
    private val netRep: CardNetRepository,
    private val cardStorageRep: CardStorageRepository
) {

    operator fun invoke(number: String) {
        val card = netRep.bindPhysicalCard(number)
        cardStorageRep.save(card)
    }
}
