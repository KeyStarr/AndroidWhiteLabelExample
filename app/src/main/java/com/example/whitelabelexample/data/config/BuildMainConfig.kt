package com.example.whitelabelexample.data.config

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.MainConfig
import com.example.whitelabelexample.domain.models.ProjectModule
import com.example.whitelabelexample.domain.models.NavigationTab
import java.lang.IllegalStateException

class BuildMainConfig : MainConfig {

    override fun mainTab(): NavigationTab {
        val mainTab = BuildConfig.MAIN_TAB
        val module = convertTabToModule(mainTab)
        if (isModuleEnabled(module).not()) {
            throw IllegalStateException("Can't have a disabled module for main tab ($mainTab) - fix config!")
        }
        return mainTab
    }

    private fun convertTabToModule(tab: NavigationTab) = when (tab) {
        NavigationTab.CARD -> ProjectModule.LOYALTY
        NavigationTab.SHOWCASE -> ProjectModule.SHOWCASE
        NavigationTab.SHOPS -> ProjectModule.SHOPS
    }

    override fun isModuleEnabled(module: ProjectModule) = BuildConfig.APP_MODULES.contains(module)
}