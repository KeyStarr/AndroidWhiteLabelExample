package com.example.whitelabelexample.ui.common.databinding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
