package com.example.whitelabelexample.domain.repositories.storage

interface IsCardObtainedStorageRepository {

    fun get(): Boolean?

    fun set(value: Boolean)
}
