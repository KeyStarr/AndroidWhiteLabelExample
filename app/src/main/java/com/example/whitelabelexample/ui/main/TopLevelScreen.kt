package com.example.whitelabelexample.ui.main

import com.example.whitelabelexample.ui.enteruserid.EnterUserIdFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

internal abstract class TopLevelScreen : SupportAppScreen() {

    class Card : TopLevelScreen() {

        override fun getFragment() = EnterUserIdFragment()
    }
}