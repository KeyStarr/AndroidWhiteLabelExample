package com.example.whitelabelexample.ui.main

import com.example.whitelabelexample.ui.bindcard.BindCardFragment
import com.example.whitelabelexample.ui.cardinfo.CardInfoFragment
import com.example.whitelabelexample.ui.enteruserid.EnterUserIdFragment
import com.example.whitelabelexample.ui.getcard.GetCardFragment
import com.example.whitelabelexample.ui.nocard.NoCardFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class ProjectScreen : SupportAppScreen() {

    class EnterUserId : ProjectScreen() {

        override fun getFragment() = EnterUserIdFragment()
    }

    class NoCard : ProjectScreen() {
        override fun getFragment() = NoCardFragment()
    }

    class GetCard : ProjectScreen() {

        override fun getFragment() = GetCardFragment()
    }

    class BindCard : ProjectScreen() {

        override fun getFragment() = BindCardFragment()
    }

    class CardInfo : ProjectScreen() {
        override fun getFragment() = CardInfoFragment()
    }
}
