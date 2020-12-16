package com.example.whitelabelexample.domain.models

enum class UserIdType(
    val regex: String?,
    val mask: String?
) {
    PHONE(
        "[+]{1}([0-9]{11})",
        "{+7} [000] [000]-[00]-[00]"
    ),
    EMAIL(
        "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z][a-zA-Z\\-]{1,25})+",
        null
    );
}
