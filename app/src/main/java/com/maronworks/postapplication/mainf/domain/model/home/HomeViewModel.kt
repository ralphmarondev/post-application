package com.maronworks.postapplication.mainf.domain.model.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.maronworks.postapplication.core.db.DBHandler
import com.maronworks.postapplication.mainf.domain.model.newpost.PostModel

class HomeViewModel : ViewModel() {
    private var items = mutableListOf<PostModel>()

    fun getAllPosts(context: Context): MutableList<PostModel> {
        return try {
            val db = DBHandler(context)

            items.clear()
            items.addAll(db.readPost())
            items.reverse()

            items
        } catch (e: Exception) {
            Log.d("getting all post", "Error: ${e.message}")

            mutableListOf(
                PostModel(
                    userCreated = "error",
                    postContent = "error",
                    datePosted = "error"
                )
            )
        }
    }

    fun onExit(context: Context, navController: NavHostController) {
        try {
            val db = DBHandler(context)

            db.deleteAllCurrentUser()
            navController.popBackStack()
        } catch (e: Exception) {
            Log.d("on home exit", "Error: ${e.message}")
        }
    }

    // post card
    private var expanded = mutableStateOf(false)

    fun isExpanded(): Boolean {
        return expanded.value
    }

    fun onExpand() {
        expanded.value = !expanded.value
    }
}