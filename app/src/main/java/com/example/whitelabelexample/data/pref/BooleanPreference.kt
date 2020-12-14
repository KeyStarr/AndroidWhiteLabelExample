package com.example.whitelabelexample.data.pref

import android.content.SharedPreferences

open class BooleanPreference(
    private val sharedPreferences: SharedPreferences,
    private val key: String
) {

    private val unusedValue: Boolean = false

    fun get(): Boolean? {
        return if (isSet()) sharedPreferences.getBoolean(key, unusedValue) else null
    }

    fun set(value: Boolean) = sharedPreferences.edit().putBoolean(key, value).apply()

    private fun isSet(): Boolean = sharedPreferences.contains(key)

}
