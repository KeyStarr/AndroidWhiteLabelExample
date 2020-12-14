package com.example.whitelabelexample.ui.cardinfo

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.models.Card
import com.example.whitelabelexample.domain.config.CardInfoConfig
import com.example.whitelabelexample.domain.usecase.GetCardUseCase
import com.example.whitelabelexample.ui.cardinfo.CardInfoViewModel.ScreenState.Content
import com.example.whitelabelexample.ui.cardinfo.CardInfoViewModel.ScreenState.Loading
import com.example.whitelabelexample.ui.cardinfo.barcode.ZxingBarcodeTypesFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardInfoViewModel(
    private val getCardUseCase: GetCardUseCase,
    private val configRep: CardInfoConfig
) : BaseViewModel() {

    val barcodeType by lazy {
        val factory = ZxingBarcodeTypesFactory()
        val configType = configRep.barcodeType()
        factory.create(configType)
    }
    val cardMask by lazy {
        configRep.cardMask()
    }

    val screenState by lazy {
        MutableLiveData<ScreenState>()
    }

    init {
        screenState.value = Loading
        loadCard()
    }

    fun onRefreshClick() {
        loadCard()
    }

    private fun loadCard() {
        launch {
            val card = withContext(Dispatchers.IO) { getCardUseCase() }
            card?.let { screenState.value = Content(it) }
        }
    }

    sealed class ScreenState {
        object Loading : ScreenState()

        class Content(val card: Card) : ScreenState()
    }
}
