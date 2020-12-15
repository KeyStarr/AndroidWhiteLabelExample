package com.example.whitelabelexample.di

import com.example.whitelabelexample.domain.usecase.auth.*
import com.example.whitelabelexample.domain.usecase.card.*
import com.example.whitelabelexample.domain.usecase.main.GetEnabledModulesUseCase
import com.example.whitelabelexample.domain.usecase.main.GetMainTabUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideDomain() = module {
    provideCard()
    provideAuth()
    provideMain()
}

private fun Module.provideCard() {
    single { BindCardUseCase(get(), get()) }
    single { GenerateCardUseCase(get(), get()) }
    single { GetCardUseCase(get(), get(), get()) }
    single { HasCardUseCase(get()) }
    single { GetBarcodeTypeUseCase(get()) }
    single { GetCardFormFieldsUseCase(get()) }
    single { GetCardNumberMaskUseCase(get()) }
    single { GetObtainMethodsUseCase(get()) }
}

private fun Module.provideAuth() {
    single { LoginUseCase(get(), get(), get()) }
    single { ValidateUserIdUseCase(get()) }
    single { IsAuthorizedUseCase(get()) }
    single { GetUserIdParamsUseCase(get()) }
    single { GetUserIdUseCase(get()) }
}

private fun Module.provideMain() {
    single { GetEnabledModulesUseCase(get()) }
    single { GetMainTabUseCase(get()) }
}
