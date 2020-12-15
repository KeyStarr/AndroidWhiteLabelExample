package com.example.whitelabelexample.data.net

import com.example.whitelabelexample.BuildConfig
import com.example.whitelabelexample.domain.models.Card
import com.example.whitelabelexample.domain.repositories.net.CardNetRepository
import com.example.whitelabelexample.domain.repositories.net.NetConnectionChecker
import java.io.IOException

class MockCardNetRepository(private val netConnectionChecker: NetConnectionChecker) :
    CardNetRepository {

    companion object {
        private val mockCardFull = Card(
            "123 45678 1234 5678 1234",
            "Из сети",
            25,
            1000
        )
        private val mockCardShort = Card(
            "1312 5435 7654 4234",
            "Из сети",
            null,
            33
        )
    }

    override fun getCard() = if (netConnectionChecker.isActive()) {
        if (BuildConfig.FLAVOR == "loyaka") mockCardFull else mockCardShort
    } else {
        throw IOException("No internet")
    }

    override fun bindPhysicalCard(number: String) {
        // pass
    }

    override fun generateCard(fieldsMap: Map<String, String>) {
        // pass
    }
}
