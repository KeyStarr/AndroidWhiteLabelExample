package com.example.whitelabelexample.data.pref

import android.content.SharedPreferences
import com.example.whitelabelexample.ui.common.prefs.StringPreference
import com.example.whitelabelexample.domain.repositories.storage.AuthTokenStorageRepository

class AuthTokenPreference(preferences: SharedPreferences) :
    StringPreference(preferences, "AuthToken"),
    AuthTokenStorageRepository
