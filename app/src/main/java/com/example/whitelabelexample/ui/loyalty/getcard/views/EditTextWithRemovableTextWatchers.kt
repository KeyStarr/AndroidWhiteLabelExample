package com.example.whitelabelexample.ui.loyalty.getcard.views

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class EditTextWithRemovableTextWatchers(context: Context, attrs: AttributeSet?) :
    TextInputEditText(context, attrs) {

    private val mListeners by lazy { mutableListOf<TextWatcher>() }

    override fun addTextChangedListener(watcher: TextWatcher) {
        mListeners.add(watcher)
        super.addTextChangedListener(watcher)
    }

    override fun removeTextChangedListener(watcher: TextWatcher) {
        mListeners.remove(watcher)
        super.removeTextChangedListener(watcher)
    }

    fun clearTextChangedListeners() {
        for (watcher in mListeners) {
            super.removeTextChangedListener(watcher)
        }
        mListeners.clear()
    }
}
