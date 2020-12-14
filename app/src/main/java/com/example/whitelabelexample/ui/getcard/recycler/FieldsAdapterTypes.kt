package com.example.whitelabelexample.ui.getcard.recycler

import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.delegates.AdapterDelegateType

internal enum class FieldsAdapterTypes(
    override val type: Int,
    override val viewHolderLayoutRes: Int
) : AdapterDelegateType {
    TEXT(0, R.layout.get_card_text_delegate_layout),
    CHOICE(1, R.layout.get_card_choice_delegate_layout),
    DATE(2, R.layout.get_card_date_delegate_layout)
}
