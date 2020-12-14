package com.example.whitelabelexample.domain.usecase

import com.example.whitelabelexample.domain.repositories.net.CardNetRepository

class BindCardUseCase(private val netRep: CardNetRepository) {

    operator fun invoke(number: String){
        netRep.bindPhysicalCard(number)
    }
}