package com.example.whitelabelexample.common.databinding

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.net.URI

@BindingAdapter("uri")
fun ImageView.loadUri(uri: URI) {
    val parse = Uri.parse(uri.toASCIIString())
    Glide.with(this).load(parse).into(this)
}

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
