package com.example.whitelabelexample.ui.bindcard

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.config.BindCardConfig
import com.example.whitelabelexample.domain.repositories.net.CardNetRepository
import com.example.whitelabelexample.domain.usecase.BindCardUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class BindCardViewModel(
    private val configRep: BindCardConfig,
    private val bindCardUseCase: BindCardUseCase
) : BaseViewModel() {

    private lateinit var lastRawInput: String

    val cardNumberInputMask by lazy {
        configRep.cardNumberInputMask()
    }
    val screenState by lazy {
        MutableLiveData<ScreenState>().apply { value = ScreenState.ReadyInput }
    }
    val isNextButtonEnabled by lazy{
        MutableLiveData<Boolean>().apply { value = false }
    }

    fun onInputChange(maskFilled: Boolean, rawInput: String) {
        isNextButtonEnabled.value = maskFilled
        lastRawInput = rawInput
        hideErrorIfNeed()
    }

    private fun hideErrorIfNeed(){
        if (screenState.value != ScreenState.ReadyInput)
            screenState.value = ScreenState.ReadyInput
    }

    fun onNextButtonClick(){
        launch { bindPhysicalCard(lastRawInput) }
    }

    private suspend fun bindPhysicalCard(number: String) {
        screenState.value = ScreenState.Loading
        withContext(Dispatchers.IO) {
            bindCardUseCase(number)
        }
        // TODO: move
    }

    sealed class ScreenState{

        object ReadyInput: ScreenState()

        object Loading: ScreenState()
    }
}
