package com.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Cache @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val KEY_DARK_THEME = "dark_theme"
    }

    fun loadDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(KEY_DARK_THEME, false)
    }

    fun saveDarkTheme(isDarkTheme: Boolean) {
        sharedPreferences.edit()
            .putBoolean(KEY_DARK_THEME, isDarkTheme)
            .apply()
    }
}