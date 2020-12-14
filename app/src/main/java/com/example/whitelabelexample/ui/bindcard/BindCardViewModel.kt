package com.example.whitelabelexample.ui.bindcard

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.config.BindCardConfig
import com.example.whitelabelexample.domain.repositories.net.CardNetRepository
import com.example.whitelabelexample.domain.usecase.BindCardUseCase
import com.example.whitelabelexample.ui.main.ProjectScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.terrakok.cicerone.Router

class BindCardViewModel(
    private val configRep: BindCardConfig,
    private val bindCardUseCase: BindCardUseCase,
    private val router: Router
) : BaseViewModel() {

    private lateinit var lastRawInput: String

    val cardNumberInputMask by lazy {
        configRep.cardNumberInputMask()
    }
    val isNextButtonEnabled by lazy{
        MutableLiveData<Boolean>().apply { value = false }
    }

    fun onInputChange(maskFilled: Boolean, rawInput: String) {
        isNextButtonEnabled.value = maskFilled
        lastRawInput = rawInput
    }

    fun onNextButtonClick(){
        launch { bindPhysicalCard(lastRawInput) }
    }

    private suspend fun bindPhysicalCard(number: String) {
        withContext(Dispatchers.IO) { bindCardUseCase(number) }
        val screen = ProjectScreen.CardInfo()
        router.newRootScreen(screen)
    }
}
