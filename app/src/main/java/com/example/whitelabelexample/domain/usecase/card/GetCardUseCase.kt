package com.example.whitelabelexample.domain.usecase.card

import com.example.whitelabelexample.domain.config.CardConfig
import com.example.whitelabelexample.domain.models.Card
import com.example.whitelabelexample.domain.repositories.net.CardNetRepository
import com.example.whitelabelexample.domain.repositories.storage.CardStorageRepository

class GetCardUseCase(
    private val netRep: CardNetRepository,
    private val storageRep: CardStorageRepository,
    private val config: CardConfig
) {

    operator fun invoke(): Card? {
        return if (config.isCacheCard()) {
            try {
                val card = netRep.getCard()
                storageRep.set(card)
                card
            } catch (exception: Exception) {
                return storageRep.get()?.copy(status = "Из кэша")
            }
        } else {
            netRep.getCard()
        }
    }
}
