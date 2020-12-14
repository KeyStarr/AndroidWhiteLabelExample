package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.MainConfig
import com.example.whitelabelexample.domain.models.ProjectModule

class BuildMainConfig : MainConfig {

    override fun mainScreen() = BuildConfig.MAIN_SCREEN

    override fun isModuleEnabled(module: ProjectModule) = BuildConfig.APP_MODULES.contains(module)
}