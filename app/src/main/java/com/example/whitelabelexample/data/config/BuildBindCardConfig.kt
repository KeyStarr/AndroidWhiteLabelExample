package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.BindCardConfig

class BuildBindCardConfig : BindCardConfig {

    override fun cardNumberInputMask(): String = BuildConfig.CARD_NUMBER_MASK
}
