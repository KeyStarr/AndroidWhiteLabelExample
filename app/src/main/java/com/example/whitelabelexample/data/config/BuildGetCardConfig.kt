package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.GetCardConfig
import com.example.whitelabelexample.domain.models.CardField

class BuildGetCardConfig : GetCardConfig {

    override fun getFields(): List<CardField> = BuildConfig.GENERATE_CARD_FIELDS
}
