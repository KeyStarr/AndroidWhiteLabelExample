package com.example.whitelabelexample.ui.common.ext

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.whitelabelexample.R
import com.redmadrobot.inputmask.helper.Mask
import com.redmadrobot.inputmask.model.CaretString

fun Toolbar.initBackButton(activity: Activity?, @DrawableRes iconRes: Int = R.drawable.ic_back){
    val backDrawable = ContextCompat.getDrawable(context!!, iconRes)!!.mutate()
    val activeColor = ContextCompat.getColor(context!!, R.color.active)
    DrawableCompat.setTint(backDrawable, activeColor)
    navigationIcon = backDrawable
    setNavigationOnClickListener { activity?.onBackPressed() }
}

fun Mask.format(string: String) =
    apply(CaretString(string, 0), false).formattedText.string
