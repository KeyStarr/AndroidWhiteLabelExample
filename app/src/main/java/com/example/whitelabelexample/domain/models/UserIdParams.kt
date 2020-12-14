package com.example.whitelabelexample.domain.models

data class UserIdParams(
    val type: UserIdType,
    val mask: String?,
    val regex: String?
)