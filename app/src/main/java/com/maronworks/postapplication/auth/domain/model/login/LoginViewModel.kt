package com.maronworks.postapplication.auth.domain.model.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.maronworks.postapplication.core.db.DBHandler
import com.maronworks.postapplication.core.navigation.Screens

class LoginViewModel : ViewModel() {

    var selectedTab = mutableIntStateOf(0)

    var fullName = mutableStateOf("")
    var username = mutableStateOf("")
    var password = mutableStateOf("")

    fun onChangeTab(tabIndex: Int) {
        selectedTab.intValue = tabIndex
    }

    fun onLogin(navController: NavHostController, context: Context) {
        try {
            val db = DBHandler(context)

            if (db.isUserExists(
                    username = this.username.value,
                    password = this.password.value
                )
            ) {
                Log.d(
                    "login",
                    "Login successful. Value: ${
                        db.isUserExists(
                            this.username.value,
                            this.password.value
                        )
                    }"
                )
                navController.navigate(Screens.MainF.route)
            } else {
                Log.d("login", "Failed: Invalid credentials")
                showToast(context, "Invalid credentials. Please try again.")
            }
        } catch (e: Exception) {
            Log.d("login", "Error: ${e.message}")
        }
    }

    fun onRegister(
        context: Context
    ) {
        try {
            val db = DBHandler(context)
            db.addUser(this.fullName.value, this.username.value, this.password.value)

            Log.d("register", "[${this.username.value}] registered successfully!")
            showToast(context, "[${this.username.value}] registered successfully!")
            selectedTab.intValue = 0
        } catch (e: Exception) {
            showToast(context, "Registration Failed. Error: ${e.message}")
            Log.d("register", "Error: ${e.message}")
        }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}