package com.example.whitelabelexample.di

import com.example.whitelabelexample.domain.usecase.*
import org.koin.dsl.module

fun provideDomain() = module {
    single { BindCardUseCase(get()) }
    single { GenerateCardUseCase(get()) }
    single { GetCardUseCase(get(), get(), get()) }
    single { LoginUseCase(get(), get(), get()) }
    single { ValidateUserIdUseCase(get()) }
}
