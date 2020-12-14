package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.CardConfig
import com.example.whitelabelexample.domain.models.BarcodeType
import com.example.whitelabelexample.domain.models.CardField
import com.example.whitelabelexample.domain.models.ObtainCardMethod

class BuildCardConfig : CardConfig {

    override fun numberMask(): String = BuildConfig.CARD_NUMBER_MASK

    override fun barcodeType(): BarcodeType = BuildConfig.BARCODE_TYPE

    override fun obtainmentMethods(): List<ObtainCardMethod> = BuildConfig.OBTAIN_CARD_METHODS

    override fun virtualFormFields(): List<CardField> = BuildConfig.GENERATE_CARD_FIELDS

    override fun isCacheCard() = BuildConfig.IS_CACHE_CARD
}
