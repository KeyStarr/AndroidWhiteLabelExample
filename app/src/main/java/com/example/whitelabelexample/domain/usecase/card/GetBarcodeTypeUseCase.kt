package com.example.whitelabelexample.domain.usecase.card

import com.example.whitelabelexample.domain.config.CardConfig

class GetBarcodeTypeUseCase(private val config: CardConfig) {

    operator fun invoke() = config.barcodeType()
}