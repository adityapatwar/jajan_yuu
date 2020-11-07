package com.nmproduction.jajanyuu.data.preferences

import android.content.Context
import androidx.preference.PreferenceManager


class AlphaPreferences(context: Context) {
    fun clearAll() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val uidModel = "data.sources.preferences.UID"
        private const val phoneModel = "data.sources.preferences.PHONE"
        private const val nameModel = "data.sources.preferences.NAME"
        private const val imageModel = "data.sources.preferences.IMAGE"
        private const val emailModel = "data.sources.preferences.EMAIL"

    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)
    var uid = preferences.getString(uidModel, "")
        set(value) = preferences.edit().putString(uidModel, value).apply()

    var name = preferences.getString(nameModel, "")
        set(value) = preferences.edit().putString(nameModel, value).apply()

    var image = preferences.getString(imageModel, "")
        set(value) = preferences.edit().putString(imageModel, value).apply()

    var email = preferences.getString(emailModel, "")
        set(value) = preferences.edit().putString(emailModel, value).apply()

    var phone = preferences.getString(phoneModel, "")
        set(value) = preferences.edit().putString(phoneModel, value).apply()

}