package com.example.whitelabelexample.domain.config

import com.example.whitelabelexample.domain.models.CardField

interface GetCardConfig {

    fun getFields(): List<CardField>
}
