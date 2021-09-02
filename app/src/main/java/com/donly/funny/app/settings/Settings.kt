package com.donly.funny.app.settings

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Settings @Inject constructor(context: Context) {
    companion object {
        const val SETTINGS_NAME = "laughter"
        const val SETTING_LANGUAGE = "com.donly.laugh.app.settings.language"
    }

    var preferences: SharedPreferences =
        context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE)
    val language by StringPreferenceDelegate(preferences, SETTING_LANGUAGE, "vn")

}