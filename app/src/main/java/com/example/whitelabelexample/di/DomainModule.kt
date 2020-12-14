package com.example.whitelabelexample.di

import com.example.whitelabelexample.domain.usecase.*
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
