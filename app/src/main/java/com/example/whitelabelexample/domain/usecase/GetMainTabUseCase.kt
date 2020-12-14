package com.example.whitelabelexample.domain.usecase

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.config.MainConfig
import com.example.whitelabelexample.domain.models.NavigationTab
import com.example.whitelabelexample.domain.models.tabsByModules
import java.lang.IllegalStateException

class GetMainTabUseCase(
    private val mainConfig: MainConfig
) {

    operator fun invoke(): NavigationTab {
        val mainTab = mainConfig.mainTab()
        val module = tabsByModules.entries.find { it.value == mainTab }!!
        val isModuleEnabled = BuildConfig.APP_MODULES.contains(module.key)
        if (isModuleEnabled.not()) {
            throw IllegalStateException("Can't have a disabled module for main tab ($mainTab) - fix config!")
        }
        return mainTab
    }
}
