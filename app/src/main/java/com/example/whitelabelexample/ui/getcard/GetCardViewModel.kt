package com.example.whitelabelexample.ui.getcard

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.config.GetCardConfig
import com.example.whitelabelexample.domain.usecase.GenerateCardUseCase
import com.example.whitelabelexample.ui.getcard.GetCardViewModel.ScreenState.Content
import com.example.whitelabelexample.ui.getcard.GetCardViewModel.ScreenState.Loading
import com.example.whitelabelexample.ui.getcard.model.FieldItem
import com.example.whitelabelexample.ui.getcard.model.UiGetCardFieldItemsFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


internal class GetCardViewModel(
    private val generateCardUseCase: GenerateCardUseCase,
    private val configRep: GetCardConfig,
    private val uiItemsFactory: UiGetCardFieldItemsFactory
) : BaseViewModel() {

    private val fieldItems by lazy {
        val fields = configRep.getFields()
        uiItemsFactory.create(fields)
    }

    val isNextButtonEnabled by lazy {
        MutableLiveData<Boolean>().apply { value = false }
    }

    val screenState by lazy { MutableLiveData<ScreenState>() }

    init {
        showFields()
    }

    private fun showFields() {
        screenState.value = Content(fieldItems)
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
            screenState.value = Loading
            withContext(Dispatchers.IO) {
                val fieldsMap = fieldItems.associateBy({ it.id }, { it.input })
                generateCardUseCase(fieldsMap)
            }
            // TODO: move
        }
    }

    sealed class ScreenState {

        object Loading : ScreenState()

        class Content(val fields: List<FieldItem>) : ScreenState()
    }
}
