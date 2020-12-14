package com.example.whitelabelexample.di

import com.example.whitelabelexample.ui.loyalty.bindcard.BindCardViewModel
import com.example.whitelabelexample.ui.loyalty.cardinfo.CardInfoViewModel
import com.example.whitelabelexample.ui.loyalty.enteruserid.EnterUserIdViewModel
import com.example.whitelabelexample.ui.loyalty.getcard.GetCardViewModel
import com.example.whitelabelexample.ui.loyalty.getcard.model.UiGetCardFieldItemsFactory
import com.example.whitelabelexample.ui.main.MainViewModel
import com.example.whitelabelexample.ui.loyalty.nocard.NoCardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone

fun provideUi() = module {
    provideNavigation()
    provideViewModels()
    single { UiGetCardFieldItemsFactory() }
}

private fun Module.provideNavigation() {
    val router = Cicerone.create(ru.terrakok.cicerone.Router())
    single { router.navigatorHolder }
    single { router.router }
}

private fun Module.provideViewModels() {
    viewModel { BindCardViewModel(get(), get(), get()) }
    viewModel { CardInfoViewModel(get(), get()) }
    viewModel { EnterUserIdViewModel(get(), get(), get(), get()) }
    viewModel { GetCardViewModel(get(), get(), get(), get()) }
    viewModel { MainViewModel(get(), get(), get(), get()) }
    viewModel { NoCardViewModel(get(), get(), get()) }
}
