package com.example.whitelabelexample.data.pref

import android.content.SharedPreferences
import com.example.whitelabelexample.common.prefs.StringPreference
import com.example.whitelabelexample.domain.repositories.storage.UserIdStorageRepository

class UserIdPreference(sharedPreferences: SharedPreferences) :
    StringPreference(sharedPreferences, "UserId"),
    UserIdStorageRepository