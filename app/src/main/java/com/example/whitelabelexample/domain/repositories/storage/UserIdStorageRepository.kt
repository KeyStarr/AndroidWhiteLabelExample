package com.example.whitelabelexample.domain.repositories.storage

interface UserIdStorageRepository {

    fun get(): String?

    fun set(value: String?)
}
