package com.example.whitelabelexample.ui.main

import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.config.MainConfig
import com.example.whitelabelexample.domain.models.NavigationTab
import com.example.whitelabelexample.domain.usecase.HasCardUseCase
import com.example.whitelabelexample.domain.usecase.IsAuthorizedUseCase
import ru.terrakok.cicerone.Router

class MainViewModel(
    config: MainConfig,
    private val router: Router,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val hasCardUseCase: HasCardUseCase
) : BaseViewModel() {

    val mainScreen = config.mainTab()

    init {
        openTabScreen(mainScreen)
    }

    fun onTabClick(navigationTab: NavigationTab) {
        openTabScreen(navigationTab)
    }

    private fun openTabScreen(navigationTab: NavigationTab) {
        when (navigationTab) {
            NavigationTab.CARD -> openCard()
            NavigationTab.SHOWCASE -> openShowcase()
            NavigationTab.SHOPS -> openShops()
        }
    }

    private fun openCard() {
        val screen = if (isAuthorizedUseCase()) {
            if (hasCardUseCase()) {
                ProjectScreen.CardInfo()
            } else {
                ProjectScreen.NoCard()
            }
        } else {
            ProjectScreen.EnterUserId()
        }
        router.newRootScreen(screen)
    }

    private fun openShowcase() {
        val screen = ProjectScreen.Showcase()
        router.newRootScreen(screen)
    }

    private fun openShops() {
        val screen = ProjectScreen.Shops()
        router.newRootScreen(screen)
    }
}
