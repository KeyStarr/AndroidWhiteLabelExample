package com.example.whitelabelexample.di

import androidx.preference.PreferenceManager
import com.example.whitelabelexample.data.config.*
import com.example.whitelabelexample.data.device.NetConnectionCheckerImpl
import com.example.whitelabelexample.data.net.MockAuthNetRepository
import com.example.whitelabelexample.data.net.MockCardNetRepository
import com.example.whitelabelexample.data.pref.AuthTokenPreference
import com.example.whitelabelexample.data.pref.CardObjectPreference
import com.example.whitelabelexample.data.pref.IsCardObtainedPreference
import com.example.whitelabelexample.data.pref.UserIdPreference
import com.example.whitelabelexample.domain.config.*
import com.example.whitelabelexample.domain.repositories.net.AuthNetRepository
import com.example.whitelabelexample.domain.repositories.net.CardNetRepository
import com.example.whitelabelexample.domain.repositories.net.NetConnectionChecker
import com.example.whitelabelexample.domain.repositories.storage.AuthTokenStorageRepository
import com.example.whitelabelexample.domain.repositories.storage.CardStorageRepository
import com.example.whitelabelexample.domain.repositories.storage.IsCardObtainedStorageRepository
import com.example.whitelabelexample.domain.repositories.storage.UserIdStorageRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideData() = module {
    provideNet()
    providePrefs()
    provideConfigs()
}

private fun Module.provideNet() {
    single<AuthNetRepository> { MockAuthNetRepository() }
    single<CardNetRepository> { MockCardNetRepository(get()) }
    single<NetConnectionChecker> { NetConnectionCheckerImpl(get()) }
}

private fun Module.providePrefs() {
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
    single<AuthTokenStorageRepository> { AuthTokenPreference(get()) }
    single<CardStorageRepository> { CardObjectPreference(get()) }
    single<UserIdStorageRepository> { UserIdPreference(get()) }
    single<IsCardObtainedStorageRepository> { IsCardObtainedPreference(get()) }
}

private fun Module.provideConfigs() {
    single<BindCardConfig> { BuildBindCardConfig() }
    single<CardInfoConfig> { BuildCardInfoConfig() }
    single<EnterUserIdConfig> { BuildEnterUserIdConfig() }
    single<GetCardConfig> { BuildGetCardConfig() }
    single<NoCardConfig> { BuildNoCardConfig() }
    single<MainConfig> { BuildMainConfig() }
}
