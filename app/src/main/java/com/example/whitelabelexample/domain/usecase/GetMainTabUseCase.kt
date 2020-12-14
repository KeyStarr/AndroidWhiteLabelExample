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
        val mainModule = tabsByModules.entries.find { it.value == mainTab }!!.key
        val isModuleEnabled = BuildConfig.APP_MODULES.contains(mainModule)
        if (isModuleEnabled.not()) {
            throw IllegalStateException("Can't use a tab ($mainTab) as main, it's module is disabled  - fix config!")
        }
        return mainTab
    }
}
