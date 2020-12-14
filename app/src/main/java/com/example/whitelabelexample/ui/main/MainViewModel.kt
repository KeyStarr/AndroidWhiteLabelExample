package com.example.whitelabelexample.ui.main

import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.config.MainConfig
import com.example.whitelabelexample.domain.models.MainScreen
import com.example.whitelabelexample.domain.usecase.HasCardUseCase
import com.example.whitelabelexample.domain.usecase.IsAuthorizedUseCase
import ru.terrakok.cicerone.Router

class MainViewModel(
    config: MainConfig,
    private val router: Router,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val hasCardUseCase: HasCardUseCase
) : BaseViewModel() {

    val mainScreen = config.mainScreen()

    init {
        openStartScreen()
    }

    private fun openStartScreen() {
        when (mainScreen) {
            MainScreen.CARD -> openCardScreen()
            MainScreen.SHOWCASE -> openShowcaseScreen()
        }
    }

    fun onCardClick() {
        openCardScreen()
    }

    private fun openCardScreen() {
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

    fun onShowcaseClick() {
        openShowcaseScreen()
    }

    private fun openShowcaseScreen(){

    }
}
