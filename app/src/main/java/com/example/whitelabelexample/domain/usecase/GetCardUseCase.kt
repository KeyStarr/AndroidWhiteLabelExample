package com.example.whitelabelexample.domain.usecase

import com.example.whitelabelexample.domain.models.Card
import com.example.whitelabelexample.domain.config.CardInfoConfig
import com.example.whitelabelexample.domain.repositories.net.CardNetRepository
import com.example.whitelabelexample.domain.repositories.storage.CardStorageRepository

class GetCardUseCase(
    private val netRep: CardNetRepository,
    private val storageRep: CardStorageRepository,
    private val config: CardInfoConfig
) {

    operator fun invoke(): Card? {
        return if (config.shouldCacheCard()) {
            try {
                val card = netRep.getCard()
                storageRep.save(card)
                card
            } catch (exception: Exception) {
                return storageRep.get()
            }
        } else {
            netRep.getCard()
        }
    }
}
