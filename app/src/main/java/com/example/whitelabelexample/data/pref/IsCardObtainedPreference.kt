package com.example.whitelabelexample.data.pref

import android.content.SharedPreferences
import com.example.whitelabelexample.domain.repositories.storage.IsCardObtainedStorageRepository

class IsCardObtainedPreference(sharedPreferences: SharedPreferences) :
    BooleanPreference(sharedPreferences, "IsCardObtained"),
    IsCardObtainedStorageRepository
