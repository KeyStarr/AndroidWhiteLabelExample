package com.example.whitelabelexample.ui.loyalty.cardinfo.barcode

import com.google.zxing.BarcodeFormat
import com.example.whitelabelexample.domain.models.BarcodeType

class ZxingBarcodeTypesMapper {

    fun create(type: BarcodeType) =
        when (type) {
            BarcodeType.EAN_13 -> BarcodeFormat.EAN_13
            BarcodeType.EAN_8 -> BarcodeFormat.EAN_8
            BarcodeType.CODE_128 -> BarcodeFormat.CODE_128
        }
}
