package com.example.whitelabelexample.ui.cardinfo.barcode

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException

interface BarcodeEncoder {

    @Throws(WriterException::class)
    fun encodeBitmap(contents: String, format: BarcodeFormat, width: Int, height: Int): Bitmap
}
