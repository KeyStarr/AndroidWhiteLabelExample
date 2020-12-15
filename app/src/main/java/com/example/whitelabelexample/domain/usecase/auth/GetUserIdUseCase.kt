package com.example.whitelabelexample.domain.usecase.auth

import com.example.whitelabelexample.domain.repositories.storage.UserIdStorageRepository

class GetUserIdUseCase(private val userIdStorageRep: UserIdStorageRepository) {

    operator fun invoke() = userIdStorageRep.get()
}