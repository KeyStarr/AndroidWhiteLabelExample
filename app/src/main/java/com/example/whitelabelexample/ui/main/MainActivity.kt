package com.example.whitelabelexample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.iterator
import com.example.whitelabelexample.R
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
        setupBottomNav()
    }

    private fun setupBottomNav() {
        setBottomNavListeners()
        hideDisabledTabs()
        selectBottomNavStartScreen()
    }

    private fun hideDisabledTabs() {
        for (item in main_bottom_navigation.menu.iterator()) {
            if (viewModel.enabledTabIds.contains(item.itemId).not()) item.isVisible = false
        }
    }

    private fun selectBottomNavStartScreen() {
        main_bottom_navigation.menu.findItem(viewModel.mainTab.itemId).isChecked = true
    }

    private fun setBottomNavListeners() {
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
        viewModel.onTabClick(itemId)
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