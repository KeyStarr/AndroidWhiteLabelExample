package com.example.whitelabelexample.domain.models

data class Card(
    val number: String,
    val status: String?,
    val discount: Int?,
    val balance: Int?
)