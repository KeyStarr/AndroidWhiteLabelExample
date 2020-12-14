package com.example.whitelabelexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.config.MainConfig
import com.example.whitelabelexample.domain.models.MainScreen
import ru.terrakok.cicerone.Router

class MainViewModel(
    private val router: Router,
    config: MainConfig
) : BaseViewModel() {

    val mainScreen = config.mainScreen()

    init {
        openStartScreen()
    }

    private fun openStartScreen() {
        when (mainScreen) {
            MainScreen.CARD -> onCardClick()
            MainScreen.SHOWCASE -> onShowcaseClick()
        }
    }

    fun onCardClick() {
        val screen = TopLevelScreen.Card()
        router.newRootScreen(screen)
    }

    fun onShowcaseClick() {

    }
}
