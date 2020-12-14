package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.MainConfig
import com.example.whitelabelexample.domain.models.ProjectModule
import com.example.whitelabelexample.domain.models.NavigationTab

class BuildMainConfig : MainConfig {

    override fun mainTab(): NavigationTab = BuildConfig.MAIN_TAB

    override fun enabledModules(): List<ProjectModule> = BuildConfig.APP_MODULES
}