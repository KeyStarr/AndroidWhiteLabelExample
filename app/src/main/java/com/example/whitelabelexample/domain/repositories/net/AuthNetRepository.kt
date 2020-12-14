package com.example.whitelabelexample.domain.repositories.net

interface AuthNetRepository {

    fun login(userId: String): String
}
