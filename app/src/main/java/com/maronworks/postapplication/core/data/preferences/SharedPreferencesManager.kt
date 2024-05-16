package com.maronworks.postapplication.core.data.preferences

import android.content.Context

class SharedPreferencesManager(private val context: Context) {
    companion object {
        const val PREF_KEY_ONBOARDING_COMPLETED = "onboarding_completed"
        const val CURRENT_USER = "current_user"
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

    // current user logged in
    fun setCurrentUser(username: String) {
        sharedPreferences.edit().putString(CURRENT_USER, username).apply()
    }

    fun getCurrentUser(): String {
        val currentUser = sharedPreferences.getString(CURRENT_USER, "root")
        return currentUser!!
    }
}