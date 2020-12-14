package com.example.whitelabelexample.common.prefs

import android.content.SharedPreferences

open class StringPreference(
    private val preferences: SharedPreferences,
    private val key: String
) {

    private val unusedValue: String = ""

    fun get(): String? {
        return if (isSet()) {
            preferences.getString(key, unusedValue)
        } else null
    }

    fun set(value: String?){
        preferences.edit().putString(key, value).commit()
    }

    fun setAsync(value: String?) = preferences.edit().putString(key, value).apply()

    fun delete() = preferences.edit().remove(key).apply()

    private fun isSet() = preferences.contains(key)

}
