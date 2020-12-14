package com.example.whitelabelexample.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class LeakSafeRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        addOnAttachStateChangeListener(object : OnAttachStateChangeListener {

            override fun onViewDetachedFromWindow(v: View?) {
                adapter = null
                removeOnAttachStateChangeListener(this)
            }

            override fun onViewAttachedToWindow(v: View?) {
                // pass
            }
        })
    }
}