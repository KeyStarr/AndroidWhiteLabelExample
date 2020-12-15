package com.example.whitelabelexample.common.prefs

import android.content.SharedPreferences
import com.google.gson.Gson


abstract class ObjectPreference<T : Any>(
    preferences: SharedPreferences,
    private val preferenceKey: String,
    private val type: Class<T>
) {

    private val gson: Gson by lazy { Gson() }
    private val stringPref: StringPreference by lazy { StringPreference(preferences, preferenceKey) }

    fun get(): T? {
        val json = stringPref.get()
        return if (json != null) {
            gson.fromJson(json, type)
        } else null
    }

    fun setAsync(value: T?) {
        val json = value?.let { gson.toJson(value) }
        stringPref.setAsync(json)
    }

    fun set(value: T?) {
        val json = value?.let { gson.toJson(value) }
        stringPref.set(json)
    }
}
