package com.example.whitelabelexample.ui.loyalty.getcard.recycler.delegates

import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import androidx.recyclerview.widget.RecyclerView
import com.example.whitelabelexample.R
import com.example.whitelabelexample.ui.common.delegates.BaseDelegatesAdapterItem
import com.example.whitelabelexample.ui.loyalty.getcard.model.ChoiceFieldItem
import com.example.whitelabelexample.ui.loyalty.getcard.model.FieldItem
import com.example.whitelabelexample.ui.loyalty.getcard.recycler.FieldsAdapterTypes
import kotlinx.android.synthetic.main.get_card_choice_delegate_layout.view.*

class ChoiceFieldDelegate(
    private val onFieldInputChanged: (FieldItem) -> Unit
) : BaseFieldAdapterDelegate(FieldsAdapterTypes.CHOICE) {

    override fun createViewHolder(view: View) =
        ChoiceViewHolder(
            view,
            onFieldInputChanged
        )

    override fun bindViewHolder(
        items: List<BaseDelegatesAdapterItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as ChoiceFieldItem
        val vh = holder as ChoiceViewHolder
        vh.bindTo(item)
    }

    class ChoiceViewHolder(
        itemView: View,
        onFieldInputChanged: (FieldItem) -> Unit
    ) : BaseFieldViewHolder(itemView, onFieldInputChanged) {

        private val nonEditableEditText = itemView.get_card_choice_non_editable_edit_text

        fun bindTo(uiItem: ChoiceFieldItem) {
            nonEditableEditText.apply {
                setHint(uiItem.hintRes)
                setText(uiItem.getChosenOptionDisplayName())
                setOnClickListener { showPopup(uiItem) }
            }
        }

        private fun showPopup(uiItem: ChoiceFieldItem) {
            ListPopupWindow(itemView.context).apply {
                width = nonEditableEditText.width
                anchorView = nonEditableEditText
                val items = uiItem.options.map { itemView.context.getString(it.displayNameRes) }
                val adapter = createAdapter(items)
                setAdapter(adapter)
                setOnItemClickListener { _, _, position, _ ->
                    onPopupItemChosen(uiItem, position)
                    dismiss()
                }
                show()
            }
        }

        private fun onPopupItemChosen(uiItem: ChoiceFieldItem, position: Int) {
            val chosenOption = uiItem.options[position]
            nonEditableEditText.setText(chosenOption.displayNameRes)
            uiItem.chosenOption = position
            onFieldInputChanged(uiItem)
        }

        private fun createAdapter(options: List<String>) =
            ArrayAdapter(itemView.context, R.layout.get_card_choice_delegate_item_layout, options)

    }
}
