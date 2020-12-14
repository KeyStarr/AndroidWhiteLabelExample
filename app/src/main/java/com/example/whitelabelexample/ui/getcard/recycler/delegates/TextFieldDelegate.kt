package com.example.whitelabelexample.ui.getcard.recycler.delegates

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.whitelabelexample.common.delegates.BaseDelegatesAdapterItem
import com.example.whitelabelexample.ui.getcard.model.FieldItem
import com.example.whitelabelexample.ui.getcard.recycler.FieldsAdapterTypes
import com.example.whitelabelexample.ui.getcard.model.TextFieldItem
import kotlinx.android.synthetic.main.get_card_text_delegate_layout.view.*

class TextFieldDelegate(
    private val onFieldInputChanged: (FieldItem) -> Unit
) : BaseFieldAdapterDelegate(FieldsAdapterTypes.TEXT) {

    override fun createViewHolder(view: View) =
        TextViewHolder(
            view,
            onFieldInputChanged
        )

    override fun bindViewHolder(
        items: List<BaseDelegatesAdapterItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as TextFieldItem
        val vh = holder as TextViewHolder
        vh.bindTo(item)
    }

    class TextViewHolder(
        itemView: View,
        onFieldInputChanged: (FieldItem) -> Unit
    ) : BaseFieldViewHolder(itemView, onFieldInputChanged) {

        private val inputLayout = itemView.get_card_text_field_input_layout
        private val editText = itemView.get_card_text_field_input_edit_text

        fun bindTo(item: TextFieldItem) {
            editText.clearTextChangedListeners()
            inputLayout.hint = itemView.context.getString(item.hintRes)
            editText.inputType = item.inputType
            editText.setText(item.input)
            setTextWatcher(item)
        }

        private fun setTextWatcher(item: TextFieldItem) {
            editText.addTextChangedListener {
                val newInput = it.toString()
                if (item.input != newInput) {
                    item.input = newInput
                    onFieldInputChanged(item)
                }
            }
        }
    }
}
