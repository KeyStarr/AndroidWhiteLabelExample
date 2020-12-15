package com.example.whitelabelexample.ui.common.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext = Dispatchers.Main + job

    override fun onCleared() {
        job.complete()
        super.onCleared()
    }
}
