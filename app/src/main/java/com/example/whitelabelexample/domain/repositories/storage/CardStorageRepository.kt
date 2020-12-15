package com.example.whitelabelexample.domain.repositories.storage

import com.example.whitelabelexample.domain.models.Card

interface CardStorageRepository {

    fun set(value: Card?)

    fun get(): Card?
}
