package com.example.whitelabelexample.di

import com.example.whitelabelexample.domain.usecase.auth.IsAuthorizedUseCase
import com.example.whitelabelexample.domain.usecase.auth.LoginUseCase
import com.example.whitelabelexample.domain.usecase.auth.ValidateUserIdUseCase
import com.example.whitelabelexample.domain.usecase.card.BindCardUseCase
import com.example.whitelabelexample.domain.usecase.card.GenerateCardUseCase
import com.example.whitelabelexample.domain.usecase.card.GetCardUseCase
import com.example.whitelabelexample.domain.usecase.card.HasCardUseCase
import com.example.whitelabelexample.domain.usecase.main.GetEnabledModulesUseCase
import com.example.whitelabelexample.domain.usecase.main.GetMainTabUseCase
import org.koin.dsl.module

fun provideDomain() = module {
    single { BindCardUseCase(get(), get()) }
    single { GenerateCardUseCase(get(), get()) }
    single { GetCardUseCase(get(), get(), get()) }
    single { LoginUseCase(get(), get(), get()) }
    single { ValidateUserIdUseCase(get()) }
    single { HasCardUseCase(get()) }
    single { IsAuthorizedUseCase(get()) }
    single { GetEnabledModulesUseCase(get()) }
    single { GetMainTabUseCase(get()) }
}
