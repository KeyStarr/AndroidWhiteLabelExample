package com.example.whitelabelexample.ui.loyalty.bindcard

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.config.CardConfig
import com.example.whitelabelexample.domain.usecase.card.BindCardUseCase
import com.example.whitelabelexample.ui.main.ProjectScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.terrakok.cicerone.Router

class BindCardViewModel(
    private val config: CardConfig,
    private val bindCardUseCase: BindCardUseCase,
    private val router: Router
) : BaseViewModel() {

    private lateinit var lastRawInput: String

    val cardNumberInputMask by lazy { config.numberMask() }
    val isNextButtonEnabled by lazy { MutableLiveData<Boolean>().apply { value = false } }

    fun onInputChange(maskFilled: Boolean, rawInput: String) {
        isNextButtonEnabled.value = maskFilled
        lastRawInput = rawInput
    }

    fun onNextButtonClick() {
        launch { bindPhysicalCard(lastRawInput) }
    }

    private suspend fun bindPhysicalCard(number: String) {
        withContext(Dispatchers.IO) { bindCardUseCase(number) }
        val screen = ProjectScreen.CardInfo()
        router.newRootScreen(screen)
    }
}
