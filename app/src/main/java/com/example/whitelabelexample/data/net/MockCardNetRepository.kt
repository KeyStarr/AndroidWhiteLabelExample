package com.example.whitelabelexample.data.net

import com.example.whitelabelexample.domain.models.Card
import com.example.whitelabelexample.domain.repositories.net.CardNetRepository
import com.example.whitelabelexample.domain.repositories.net.NetConnectionChecker
import java.io.IOException

class MockCardNetRepository(private val netConnectionChecker: NetConnectionChecker) :
    CardNetRepository {

    companion object {
        private val mockCard = Card(
            "1312 5435 7654 4234",
            "Из сети",
            25,
            1000
        )
    }

    override fun getCard() = if (netConnectionChecker.isActive()) mockCard else throw IOException("No internet")

    override fun bindPhysicalCard(number: String) = mockCard

    override fun generateCard(fieldsMap: Map<String, String>) = mockCard
}
