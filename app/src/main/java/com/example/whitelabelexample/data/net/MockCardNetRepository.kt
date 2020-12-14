package com.example.whitelabelexample.data.net

import com.example.whitelabelexample.domain.models.Card
import com.example.whitelabelexample.domain.repositories.net.CardNetRepository

class MockCardNetRepository : CardNetRepository {

    companion object {
        private val mockCard = Card(
            "1312 5435 7654 4234",
            "Gold",
            25,
            1000
        )
    }

    override fun getCard() = mockCard

    override fun bindPhysicalCard(number: String) = mockCard

    override fun generateCard(fieldsMap: Map<String, String>) = mockCard
}
