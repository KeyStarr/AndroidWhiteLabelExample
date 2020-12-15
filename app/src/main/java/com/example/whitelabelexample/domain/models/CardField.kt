package com.example.whitelabelexample.domain.models

import com.example.whitelabelexample.R

enum class CardField(
    val serverName: String,
    val displayNameRes: Int
) {

    FIRST_NAME("first_name", R.string.card_first_name),
    LAST_NAME("last_name", R.string.card_last_name),
    BIRTHDAY("birthday", R.string.card_birthday),
    EMAIL("email", R.string.card_email),
    GENDER("gender", R.string.card_gender);
}

interface CardFieldChoiceValues {
    val serverName: String
    val displayNameRes: Int
}

enum class CardGenderValues(
    override val serverName: String,
    override val displayNameRes: Int
) : CardFieldChoiceValues {

    MALE("male", R.string.card_gender_male),
    FEMALE("female", R.string.card_gender_female)
}

