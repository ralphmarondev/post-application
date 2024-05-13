package com.maronworks.authentication.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    fun createUser(context: Context, user: com.maronworks.core.db.auth.UserModel): Boolean {
        try {
            val db = com.maronworks.core.db.auth.DBHandler(context)

            db.createUser(user)
            return true
        } catch (ex: Exception) {
            Log.d("db", "Error: ${ex.message}")
            return false
        }
    }

    fun isUserExists(context: Context, user: com.maronworks.core.db.auth.UserModel): Boolean {
        try {
            val db = com.maronworks.core.db.auth.DBHandler(context)

            Log.d("db", "isUserExists(${user.username} ${user.password}): ${db.isUserExist(user)}")
            if (db.isUserExist(user))
                return true
            else
                return false
        } catch (ex: Exception) {
            Log.d("db", "Error: ${ex.message}")
            return false
        }
    }
}