package com.example.whitelabelexample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.whitelabelexample.R
import com.example.whitelabelexample.domain.models.MainScreen
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

@KoinApiExtension
class MainActivity : AppCompatActivity(), KoinComponent {

    private val viewModel: MainViewModel by viewModel()

    private val navigatorHolder: NavigatorHolder by inject()

    private val navigator by lazy {
        SupportAppNavigator(this, R.id.main_fragment_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBottomBottomNavListeners()
        selectBottomNavStartScreen()
    }

    private fun selectBottomNavStartScreen() {
        val startMenuId = when (viewModel.mainScreen) {
            MainScreen.CARD -> R.id.bottom_menu_card
            MainScreen.SHOWCASE -> R.id.bottom_menu_showcase
        }
        main_bottom_navigation.menu.findItem(startMenuId).isChecked = true
    }

    private fun setBottomBottomNavListeners() {
        main_bottom_navigation.apply {
            setOnNavigationItemSelectedListener {
                onTabClickListener(it.itemId)
                true
            }
            setOnNavigationItemReselectedListener { item ->
                //nothing
            }
        }
    }

    private fun onTabClickListener(itemId: Int) {
        when (itemId) {
            R.id.bottom_menu_card -> viewModel.onCardClick()
            R.id.bottom_menu_showcase -> viewModel.onShowcaseClick()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}