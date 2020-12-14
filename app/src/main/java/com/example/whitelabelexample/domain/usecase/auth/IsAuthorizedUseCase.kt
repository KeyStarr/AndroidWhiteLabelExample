package com.example.whitelabelexample.domain.usecase.auth

import com.example.whitelabelexample.domain.repositories.storage.AuthTokenStorageRepository

class IsAuthorizedUseCase(private val authTokenStorageRep: AuthTokenStorageRepository) {

    operator fun invoke() = authTokenStorageRep.get() != null
}
