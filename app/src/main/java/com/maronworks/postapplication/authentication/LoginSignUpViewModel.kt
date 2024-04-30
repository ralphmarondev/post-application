package com.maronworks.postapplication.authentication

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.maronworks.postapplication.core.local.db.DBHandler

@SuppressLint("StaticFieldLeak")
class LoginSignUpViewModel(private val context: Context) : ViewModel() {
    var selectedScreen = mutableIntStateOf(0)

    /*
     *      Returns [true] if user exists in database else return [false]
     */
    fun onLogin(
        username: String,
        password: String,
    ): Boolean {
        // check if fields [username, password] is empty
        if (username.isEmpty() || password.isEmpty()) {
            Log.d("db", "Username or Password: are empty.")
            return false
        }

        // if fields are not empty, check if user exists in database
        try {
            val db = DBHandler(context)

            return db.isUserExists(username, password)
        } catch (ex: Exception) {
            Log.d("db", "Error: ${ex.message}")
        }
        return false
    }

    /*
     *      Returns [true] if user is registered successfully else return [false]
     */
    fun registerUser(
        username: String,
        password: String,
    ): Boolean {
        // check if fields [username, password] is empty
        if (username.isEmpty() || password.isEmpty()) {
            Log.d("db", "Username or Password: are empty.")
            return false
        }

        // if fields are not empty, register user in database
        try {
            val db = DBHandler(context)

            if (db.isUserExists(username, password)) {
                Log.d("db", "$username, $password already EXISTS in database")
            } else {
                db.insertUser(username, password)
                Log.d("db", "$username, $password registered successfully")

                return true
            }
        } catch (ex: Exception) {
            Log.d("db", "Error [registerUser()]: ${ex.message}")
        }
        return false
    }
}