package com.example.whitelabelexample.ui.main

import androidx.fragment.app.Fragment
import com.example.whitelabelexample.ui.loyalty.bindcard.BindCardFragment
import com.example.whitelabelexample.ui.loyalty.cardinfo.CardInfoFragment
import com.example.whitelabelexample.ui.loyalty.enteruserid.EnterUserIdFragment
import com.example.whitelabelexample.ui.loyalty.getcard.GetCardFragment
import com.example.whitelabelexample.ui.loyalty.nocard.NoCardFragment
import com.example.whitelabelexample.ui.shops.ShopsFragment
import com.example.whitelabelexample.ui.showcase.ShowcaseFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class ProjectScreen : SupportAppScreen() {

    class Showcase : ProjectScreen() {
        override fun getFragment() = ShowcaseFragment()
    }

    class Shops : ProjectScreen() {
        override fun getFragment() = ShopsFragment()
    }

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
