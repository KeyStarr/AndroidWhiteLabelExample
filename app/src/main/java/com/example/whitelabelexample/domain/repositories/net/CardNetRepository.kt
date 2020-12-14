package com.example.whitelabelexample.domain.repositories.net

import com.example.whitelabelexample.domain.models.Card

interface CardNetRepository {

    fun bindPhysicalCard(number: String)

    fun generateCard(fieldsMap: Map<String,String>)

    fun getCard(): Card
}
