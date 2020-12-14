package com.example.whitelabelexample.ui.loyalty.getcard.recycler.delegates

import android.app.DatePickerDialog
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.delegates.BaseDelegatesAdapterItem
import com.example.whitelabelexample.ui.loyalty.getcard.model.DateFieldItem
import com.example.whitelabelexample.ui.loyalty.getcard.model.FieldItem
import com.example.whitelabelexample.ui.loyalty.getcard.recycler.FieldsAdapterTypes
import kotlinx.android.synthetic.main.get_card_date_delegate_layout.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class DateFieldDelegate(
    private val onFieldInputChanged: (FieldItem) -> Unit
) : BaseFieldAdapterDelegate(FieldsAdapterTypes.DATE) {

    companion object {
        private val CARD_DATE_FORMATTER_UI = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }

    override fun createViewHolder(view: View) =
        DateViewHolder(
            view,
            onFieldInputChanged
        )

    override fun bindViewHolder(
        items: List<BaseDelegatesAdapterItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as DateFieldItem
        val vh = holder as DateViewHolder
        vh.bindTo(item)
    }

    class DateViewHolder(
        itemView: View,
        onFieldInputChanged: (FieldItem) -> Unit
    ) : BaseFieldViewHolder(itemView, onFieldInputChanged) {

        private val nonEditableEditText = itemView.get_card_date_non_editable_edit_text

        fun bindTo(uiItem: DateFieldItem) {
            nonEditableEditText.apply {
                setHint(uiItem.hintRes)
                setText(uiItem.input)
                setOnClickListener { showDatePicker(uiItem) }
            }
        }

        private fun showDatePicker(uiItem: DateFieldItem) {
            DatePickerDialog(
                itemView.context,
                R.style.DatePickerDialogStyle,
                { _, year, month, day -> onDateChosen(uiItem, year, month, day) },
                uiItem.maxPickerDate.get(Calendar.YEAR),
                uiItem.maxPickerDate.get(Calendar.MONTH),
                uiItem.maxPickerDate.get(Calendar.MONTH)
            ).apply {
                datePicker.minDate = uiItem.minPickerDate.timeInMillis
                datePicker.maxDate = uiItem.maxPickerDate.timeInMillis
                show()
            }
        }

        private fun onDateChosen(uiItem: DateFieldItem, year: Int, month: Int, dayOfMonth: Int) {
            val chosenDate = LocalDate.of(year, month + 1, dayOfMonth)
            val formattedDate = chosenDate.format(CARD_DATE_FORMATTER_UI)
            nonEditableEditText.setText(formattedDate)
            uiItem.input = formattedDate
            onFieldInputChanged(uiItem)
        }
    }
}
