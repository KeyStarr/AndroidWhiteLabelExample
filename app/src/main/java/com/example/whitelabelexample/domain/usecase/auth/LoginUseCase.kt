package com.example.whitelabelexample.domain.usecase.auth

import com.example.whitelabelexample.domain.repositories.storage.AuthTokenStorageRepository
import com.example.whitelabelexample.domain.repositories.storage.UserIdStorageRepository
import com.example.whitelabelexample.domain.repositories.net.AuthNetRepository

class LoginUseCase(
    private val netRepository: AuthNetRepository,
    private val authTokenStorageRepository: AuthTokenStorageRepository,
    private val userIdStorageRepository: UserIdStorageRepository
) {

    operator fun invoke(userId: String) {
        val token = netRepository.login(userId)
        authTokenStorageRepository.set(token)
        userIdStorageRepository.set(userId)
    }
}