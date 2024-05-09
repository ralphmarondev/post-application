package com.maronworks.postapplication.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.maronworks.postapplication.login.data.db.DBHandler
import com.maronworks.postapplication.login.model.user.UserModel

class LoginViewModel : ViewModel() {

    fun createUser(context: Context, user: UserModel): Boolean {
        try {
            val db = DBHandler(context)

            db.createUser(user)
            return true
        } catch (ex: Exception) {
            Log.d("db", "Error: ${ex.message}")
            return false
        }
    }

    fun isUserExists(context: Context, user: UserModel): Boolean {
        try {
            val db = DBHandler(context)

            Log.d("db", "isUserExists(${user.username} ${user.password}): ${db.isUserExist(user)}")
            return db.isUserExist(user)
        } catch (ex: Exception) {
            Log.d("db", "Error: ")
            return false
        }
    }
}