package com.maronworks.postapplication.mainf.domain.model.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.maronworks.postapplication.core.db.DBHandler

class HomeViewModel : ViewModel() {
    fun onExit(context: Context, navController: NavHostController) {
        try {
            val db = DBHandler(context)

            db.deleteAllCurrentUser()
            navController.popBackStack()
        } catch (e: Exception) {
            Log.d("on home exit", "Error: ${e.message}")
        }
    }
}