package com.example.whitelabelexample.data.net

import com.example.whitelabelexample.domain.repositories.net.AuthNetRepository

class MockAuthNetRepository : AuthNetRepository {

    override fun login(userId: String) = "token_babyyyy"
}
