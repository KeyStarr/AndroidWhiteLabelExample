package com.example.whitelabelexample.ui.loyalty.getcard

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.ui.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.usecase.card.GenerateCardUseCase
import com.example.whitelabelexample.domain.usecase.card.GetCardFormFieldsUseCase
import com.example.whitelabelexample.ui.loyalty.getcard.model.FieldItem
import com.example.whitelabelexample.ui.loyalty.getcard.model.UiGetCardFieldItemsMapper
import com.example.whitelabelexample.ui.main.ProjectScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.terrakok.cicerone.Router

class GetCardViewModel(
    private val getCardFormFieldsUseCase: GetCardFormFieldsUseCase,
    private val generateCardUseCase: GenerateCardUseCase,
    private val uiItemsMapper: UiGetCardFieldItemsMapper,
    private val router: Router
) : BaseViewModel() {

    private val fieldItems by lazy {
        val fields = getCardFormFieldsUseCase()
        uiItemsMapper.map(fields)
    }

    val isNextButtonEnabled by lazy {
        MutableLiveData<Boolean>().apply { value = false }
    }

    val fieldsData by lazy { MutableLiveData<List<FieldItem>>() }

    init {
        fieldsData.value = fieldItems
    }

    fun onFieldInputChanged(field: FieldItem) {
        field.isValid = field.validator.validate(field.input)
        isNextButtonEnabled.value = areAllFieldsValid()
    }

    private fun areAllFieldsValid() = fieldItems.find { it.isValid.not() }?.let { false } ?: true

    fun onNextButtonClick() {
        generateVirtualCard()
    }

    private fun generateVirtualCard() {
        launch {
            withContext(Dispatchers.IO) {
                val fieldsMap = fieldItems.associateBy({ it.id }, { it.input })
                generateCardUseCase(fieldsMap)
            }
            val screen = ProjectScreen.CardInfo()
            router.newRootScreen(screen)
        }
    }
}
