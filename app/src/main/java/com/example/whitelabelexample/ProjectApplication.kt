package com.example.whitelabelexample

import android.app.Application
import com.example.whitelabelexample.di.provideData
import com.example.whitelabelexample.di.provideDomain
import com.example.whitelabelexample.di.provideUi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        startKoin {
            val modules = listOf(
                provideDomain(),
                provideData(),
                provideUi()
            )
            modules(modules)
            androidContext(this@ProjectApplication)
        }
    }
}
