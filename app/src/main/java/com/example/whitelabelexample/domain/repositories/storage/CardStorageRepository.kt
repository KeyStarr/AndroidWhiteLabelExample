package com.example.whitelabelexample.domain.repositories.storage

import com.example.whitelabelexample.domain.models.Card

interface CardStorageRepository {

    fun save(value: Card?)

    fun get(): Card?
}
