package com.maronworks.postapplication.core.local.preferences

import android.content.Context

class SharedPreferenceManager(private val context: Context) {
    private companion object {
        const val ONBOARDING_KEY = "onboarding_key"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun isFirstLaunch(): Boolean {
        val completed = sharedPreferences.getBoolean(ONBOARDING_KEY, false)

        return !completed
    }

    fun setOnBoardingCompleted() {
        sharedPreferences.edit().putBoolean(ONBOARDING_KEY, true).apply()
    }
}