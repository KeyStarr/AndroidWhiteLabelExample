package com.example.whitelabelexample.domain.models

import com.example.whitelabelexample.R

enum class NavigationTab(val itemId: Int) {
    CARD(R.id.bottom_menu_card),
    SHOWCASE(R.id.bottom_menu_showcase),
    SHOPS(R.id.bottom_menu_shops)
}

val tabsByModules = mapOf(
    ProjectModule.LOYALTY to NavigationTab.CARD,
    ProjectModule.SHOPS to NavigationTab.SHOPS,
    ProjectModule.SHOWCASE to NavigationTab.SHOWCASE
)
