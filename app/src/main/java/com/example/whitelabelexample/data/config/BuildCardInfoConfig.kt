package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.CardInfoConfig
import com.example.whitelabelexample.domain.models.BarcodeType

class BuildCardInfoConfig : CardInfoConfig {

    override fun cardMask() = BuildConfig.CARD_NUMBER_MASK

    override fun barcodeType(): BarcodeType = BuildConfig.BARCODE_TYPE

    override fun shouldCacheCard(): Boolean {
        TODO("Not yet implemented")
    }
}
