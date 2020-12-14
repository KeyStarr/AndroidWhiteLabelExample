package com.example.whitelabelexample.data.pref

import android.content.SharedPreferences
import com.example.whitelabelexample.common.prefs.ObjectPreference
import com.example.whitelabelexample.domain.models.Card
import com.example.whitelabelexample.domain.repositories.storage.CardStorageRepository

class CardObjectPreference(preference: SharedPreferences) :
    ObjectPreference<Card>(preference, "Card", Card::class.java),
    CardStorageRepository