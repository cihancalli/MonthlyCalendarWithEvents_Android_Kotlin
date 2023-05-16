package com.zerdasoftware.monthlycalendarwithevents.baseclass

import android.content.Context

class LanguageManager(context: Context) {
    private val preferences = context.getSharedPreferences("Language", Context.MODE_PRIVATE)

    fun saveLanguage(language: String) {
        preferences.edit().putString("language", language).apply()
    }

    fun loadLanguage(): String? {
        return preferences.getString("language", null)
    }
}