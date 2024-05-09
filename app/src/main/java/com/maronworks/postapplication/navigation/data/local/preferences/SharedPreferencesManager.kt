package com.maronworks.postapplication.navigation.data.local.preferences

import android.content.Context

class SharedPreferencesManager(private val context: Context) {
    companion object {
        const val PREF_KEY_ONBOARDING_COMPLETED = "onboarding_completed"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun isFirstLaunch(): Boolean {
        val isCompleted = sharedPreferences.getBoolean(
            PREF_KEY_ONBOARDING_COMPLETED, false
        )
        return !isCompleted
    }

    fun setOnBoardingCompleted() {
        sharedPreferences.edit().putBoolean(
            PREF_KEY_ONBOARDING_COMPLETED,
            true
        ).apply()
    }
}