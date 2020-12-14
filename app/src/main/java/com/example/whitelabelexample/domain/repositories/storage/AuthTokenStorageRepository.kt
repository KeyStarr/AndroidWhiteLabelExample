package com.example.whitelabelexample.domain.repositories.storage

interface AuthTokenStorageRepository {

    fun get(): String?

    fun set(value: String?)
}
