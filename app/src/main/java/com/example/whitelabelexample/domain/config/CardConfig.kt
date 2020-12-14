package com.example.whitelabelexample.domain.config

import com.example.whitelabelexample.domain.models.BarcodeType
import com.example.whitelabelexample.domain.models.CardField
import com.example.whitelabelexample.domain.models.ObtainCardMethod
import com.example.whitelabelexample.domain.models.UserIdType

interface CardConfig {

    fun numberMask(): String

    fun barcodeType(): BarcodeType

    fun obtainmentMethods(): List<ObtainCardMethod>

    fun virtualFormFields(): List<CardField>

    fun shouldCacheCard(): Boolean
}
