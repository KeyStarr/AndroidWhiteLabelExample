package com.example.whitelabelexample.ui.loyalty.cardinfo

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.models.Card
import com.example.whitelabelexample.domain.usecase.card.GetBarcodeTypeUseCase
import com.example.whitelabelexample.domain.usecase.card.GetCardNumberMaskUseCase
import com.example.whitelabelexample.domain.usecase.card.GetCardUseCase
import com.example.whitelabelexample.ui.loyalty.cardinfo.barcode.ZxingBarcodeTypesMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardInfoViewModel(
    private val getCardUseCase: GetCardUseCase,
    private val getBarcodeTypeUseCase: GetBarcodeTypeUseCase,
    private val getCardNumberMaskUseCase: GetCardNumberMaskUseCase,
    barcodeTypesMapper: ZxingBarcodeTypesMapper
) : BaseViewModel() {

    val barcodeType by lazy {
        val type = getBarcodeTypeUseCase()
        barcodeTypesMapper.create(type)
    }

    val cardMask by lazy { getCardNumberMaskUseCase() }

    val cardData by lazy { MutableLiveData<Card>() }

    init {
        loadCard()
    }

    private fun loadCard() {
        launch {
            val card = withContext(Dispatchers.IO) { getCardUseCase() }
            card?.let { cardData.value = it }
        }
    }
}
