package com.example.whitelabelexample.ui.main

import com.example.whitelabelexample.ui.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.models.NavigationTab
import com.example.whitelabelexample.domain.models.tabsByModules
import com.example.whitelabelexample.domain.usecase.main.GetEnabledModulesUseCase
import com.example.whitelabelexample.domain.usecase.main.GetMainTabUseCase
import com.example.whitelabelexample.domain.usecase.card.HasCardUseCase
import com.example.whitelabelexample.domain.usecase.auth.IsAuthorizedUseCase
import ru.terrakok.cicerone.Router

class MainViewModel(
    private val router: Router,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val hasCardUseCase: HasCardUseCase,
    getEnabledModulesUseCase: GetEnabledModulesUseCase,
    getMainTabUseCase: GetMainTabUseCase
) : BaseViewModel() {

    val enabledTabIds = getEnabledModulesUseCase().mapNotNull { tabsByModules[it]?.itemId }

    val mainTab = getMainTabUseCase()

    init {
        openTabScreen(mainTab)
    }

    fun onTabClick(itemId: Int) {
        val tab = NavigationTab.values().find { it.itemId == itemId }!!
        openTabScreen(tab)
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
