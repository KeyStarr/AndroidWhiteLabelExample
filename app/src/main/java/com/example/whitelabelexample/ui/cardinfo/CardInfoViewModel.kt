package com.example.whitelabelexample.ui.cardinfo

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.models.Card
import com.example.whitelabelexample.domain.config.CardInfoConfig
import com.example.whitelabelexample.domain.usecase.GetCardUseCase
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

    val cardMask by lazy { configRep.cardMask() }

    val cardData by lazy { MutableLiveData<Card>() }

    init {
        loadCard()
    }

    fun onRefreshClick() {
        loadCard()
    }

    private fun loadCard() {
        launch {
            val card = withContext(Dispatchers.IO) { getCardUseCase() }
            card?.let { cardData.value = it }
        }
    }
}
