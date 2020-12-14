package com.example.whitelabelexample.domain.usecase.card

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.CardConfig
import com.example.whitelabelexample.domain.models.Card

class GetCardNumberMaskUseCase(
    private val config: CardConfig
) {

    operator fun invoke() = config.numberMask()
}