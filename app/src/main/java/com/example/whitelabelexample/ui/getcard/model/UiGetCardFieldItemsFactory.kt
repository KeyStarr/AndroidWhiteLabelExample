package com.example.whitelabelexample.ui.getcard.model

import android.text.InputType.*
import com.example.whitelabelexample.domain.models.CardField
import com.example.whitelabelexample.domain.models.CardField.*
import com.example.whitelabelexample.domain.models.CardGenderValues
import java.util.*

class UiGetCardFieldItemsFactory {

    companion object {
        private const val INPUT_TYPE_PERSONAL_DATA =
            TYPE_CLASS_TEXT or TYPE_TEXT_FLAG_CAP_SENTENCES
    }

    fun create(fields: List<CardField>) = fields.map { createFieldItem(it) }

    private fun createFieldItem(field: CardField) =
        when (field) {
            FIRST_NAME,
            LAST_NAME -> createPersonalDataField(field)
            BIRTHDAY -> createBirthdayField()
            GENDER -> createGenderField(field)
            EMAIL -> createEmailField(field)
        }

    private fun createPersonalDataField(field: CardField) =
        TextFieldItem(field.serverName, field.displayNameRes, INPUT_TYPE_PERSONAL_DATA)

    private fun createBirthdayField(): FieldItem {
        val minDate = Calendar.getInstance().apply {
            set(Calendar.YEAR, get(Calendar.YEAR).minus(100))
        }
        val maxDate = Calendar.getInstance().apply {
            set(Calendar.YEAR, get(Calendar.YEAR).minus(18))
        }
        return DateFieldItem(
            BIRTHDAY.serverName, BIRTHDAY.displayNameRes, minDate, maxDate
        )
    }

    private fun createGenderField(field: CardField): ChoiceFieldItem {
        return ChoiceFieldItem(
            field.serverName,
            field.displayNameRes,
            listOf(
                ChoiceOption(CardGenderValues.MALE.serverName, CardGenderValues.MALE.displayNameRes),
                ChoiceOption(CardGenderValues.MALE.serverName, CardGenderValues.MALE.displayNameRes)
            )
        )
    }

    private fun createEmailField(field: CardField) =
        TextFieldItem(field.serverName, field.displayNameRes, TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
}
