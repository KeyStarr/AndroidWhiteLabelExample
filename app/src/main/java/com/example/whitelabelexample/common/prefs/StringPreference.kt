package com.example.whitelabelexample.common.prefs

import android.annotation.SuppressLint
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

    @SuppressLint("ApplySharedPref")
    fun set(value: String?){
        preferences.edit().putString(key, value).commit()
    }

    fun setAsync(value: String?) = preferences.edit().putString(key, value).apply()

    private fun isSet() = preferences.contains(key)

}
