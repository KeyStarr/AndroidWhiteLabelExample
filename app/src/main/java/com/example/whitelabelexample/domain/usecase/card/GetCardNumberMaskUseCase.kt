package com.example.whitelabelexample.domain.usecase.card

import com.example.whitelabelexample.domain.config.CardConfig

class GetCardNumberMaskUseCase(
    private val config: CardConfig
) {

    operator fun invoke() = config.numberMask()
}