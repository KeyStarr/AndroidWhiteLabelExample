package com.example.whitelabelexample.ui.loyalty.getcard.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.ext.hideKeyboard
import kotlinx.android.synthetic.main.non_editable_edit_text_layout.view.*

class NonEditableEditText(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var onEditTextClickCallback: (() -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.non_editable_edit_text_layout, this, true)
        setEditTextClickListener()
    }

    private fun setEditTextClickListener() {
        non_editable_input_edit_text.setOnClickListener {
            non_editable_input_edit_text.hideKeyboard()
            non_editable_input_layout.requestFocus()
            onEditTextClickCallback?.invoke()
        }
    }

    fun setOnClickListener(callback: () -> Unit) {
        onEditTextClickCallback = callback
    }

    fun setHint(hintRes: Int){
        val hint = context.getString(hintRes)
        non_editable_input_layout.hint = hint
    }

    fun setHint(hint: String) {
        non_editable_input_layout.hint = hint
    }

    fun setText(textRes: Int) {
        non_editable_input_edit_text.setText(textRes)
    }

    fun setText(text: String) {
        non_editable_input_edit_text.setText(text)
    }

    fun toggleEnabled(enabled: Boolean) {
        non_editable_input_edit_text.isEnabled = enabled
    }
}
