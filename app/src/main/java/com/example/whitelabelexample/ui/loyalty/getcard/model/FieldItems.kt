package com.example.whitelabelexample.ui.loyalty.getcard.model

import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.delegates.AdapterDelegateType
import com.example.whitelabelexample.common.delegates.BaseDelegatesAdapterItem
import com.example.whitelabelexample.ui.loyalty.getcard.recycler.FieldsAdapterTypes
import com.example.whitelabelexample.ui.loyalty.getcard.NonBlankValidator
import com.example.whitelabelexample.ui.loyalty.getcard.Validator
import java.util.*
import kotlin.properties.Delegates

sealed class FieldItem(
    val id: String,
    val hintRes: Int,
    adapterType: AdapterDelegateType,
    val validator: Validator = NonBlankValidator()
) : BaseDelegatesAdapterItem(adapterType) {

    open var input = ""
    var isValid = false
}

class TextFieldItem(
    id: String,
    hintRes: Int,
    val inputType: Int
) : FieldItem(id, hintRes, FieldsAdapterTypes.TEXT)

class DateFieldItem(
    id: String,
    hintRes: Int,
    val minPickerDate: Calendar,
    val maxPickerDate: Calendar
) : FieldItem(id, hintRes, FieldsAdapterTypes.DATE)

class ChoiceFieldItem(
    id: String,
    hintRes: Int,
    val options: List<ChoiceOption>
) : FieldItem(id, hintRes, FieldsAdapterTypes.CHOICE) {

    var chosenOption by Delegates.observable(-1) { _, _, newValue ->
        input = options[newValue].serverName
    }

    fun getChosenOptionDisplayName() =
        if (chosenOption == -1) R.string.get_card_gender_empty else options[chosenOption].displayNameRes
}


class ChoiceOption(
    val serverName: String,
    val displayNameRes: Int
)
