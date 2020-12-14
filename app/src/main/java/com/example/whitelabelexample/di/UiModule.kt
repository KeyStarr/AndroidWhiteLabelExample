package com.example.whitelabelexample.di

import com.example.whitelabelexample.ui.bindcard.BindCardViewModel
import com.example.whitelabelexample.ui.cardinfo.CardInfoViewModel
import com.example.whitelabelexample.ui.enteruserid.EnterUserIdViewModel
import com.example.whitelabelexample.ui.getcard.GetCardViewModel
import com.example.whitelabelexample.ui.main.MainViewModel
import com.example.whitelabelexample.ui.nocard.NoCardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone

fun provideUi() = module {
    provideNavigation()
    provideViewModels()
}

private fun Module.provideNavigation() {
    val router = Cicerone.create(ru.terrakok.cicerone.Router())
    single { router.navigatorHolder }
    single { router.router }
}

private fun Module.provideViewModels() {
    viewModel { BindCardViewModel(get(), get()) }
    viewModel { CardInfoViewModel(get(), get()) }
    viewModel { EnterUserIdViewModel(get(), get(), get()) }
    viewModel { GetCardViewModel(get(), get(), get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { NoCardViewModel(get(), get()) }
}
