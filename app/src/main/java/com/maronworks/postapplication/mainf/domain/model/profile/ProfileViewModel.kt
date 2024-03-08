package com.maronworks.postapplication.mainf.domain.model.profile

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.maronworks.postapplication.core.db.DBHandler

class ProfileViewModel : ViewModel() {
    private var fullName = mutableStateOf("Full Name")
    private var username = mutableStateOf("")

    fun getUsername(context: Context): String {
        return try {
            val db = DBHandler(context)

            username.value = db.readCurrentUser()
            username.value // return this
        } catch (e: Exception) {
            Log.d("reading current user", "Error: ${e.message}")
            "Error [Username]!" // else return this
        }
    }

    fun getFullName(context :Context):String{
        return try {
            val db = DBHandler(context)

            fullName.value = db.getCurrentUserDetails(username.value).fullName
            fullName.value // return this
        } catch (e: Exception) {
            Log.d("reading current user", "Error: ${e.message}")
            "Error [Full Name]!" // else return this
        }
    }
}