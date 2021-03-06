package com.example.whitelabelexample.ui.loyalty.cardinfo.barcode

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix


/**
 * Helper class for encoding barcode as a Bitmap.
 *
 * Adapted from QRCodeEncoder, from the zxing project:
 * https://github.com/zxing/zxing
 *
 * Licensed under the Apache License, Version 2.0.
 */
class ZxingBarcodeEncoder : BarcodeEncoder {

    companion object {
        private const val WHITE = -0x1
        private const val BLACK = -0x1000000
    }

    @Throws(WriterException::class)
    override fun encodeBitmap(contents: String, format: BarcodeFormat, width: Int, height: Int): Bitmap {
        val matrix = encode(contents, format, width, height)
        return createBitmap(matrix)
    }

    @Throws(WriterException::class)
    private fun encode(contents: String, format: BarcodeFormat, width: Int, height: Int): BitMatrix {
        try {
            return MultiFormatWriter().encode(contents, format, width, height)
        } catch (e: WriterException) {
            throw e
        } catch (e: Exception) {
            // Zxing sometimes throws an IllegalArgumentException
            throw WriterException(e)
        }
    }

    private fun createBitmap(matrix: BitMatrix): Bitmap {
        val width = matrix.width
        val height = matrix.height
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (matrix.get(x, y)) BLACK else WHITE
            }
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }
}
