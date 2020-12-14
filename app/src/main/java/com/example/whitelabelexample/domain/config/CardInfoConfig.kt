package com.example.whitelabelexample.domain.config

import com.example.whitelabelexample.domain.models.BarcodeType

interface CardInfoConfig {

    fun cardMask(): String

    fun barcodeType(): BarcodeType

    fun shouldCacheCard(): Boolean
}
