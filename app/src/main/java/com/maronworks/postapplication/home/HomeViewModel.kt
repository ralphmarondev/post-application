package com.maronworks.postapplication.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.maronworks.postapplication.R
import com.maronworks.postapplication.home.core.db.DBHandler
import com.maronworks.postapplication.home.core.domain.post.PostModel

class HomeViewModel : ViewModel() {
    var currentUser = mutableStateOf("")
    var currentUserImage = mutableIntStateOf(R.drawable.cute_me)
    var currentDate = mutableStateOf("2024-05-12 | 7:51PM")

    fun createNewPost(context: Context, postContent: String) {
        val db = DBHandler(context)

        db.createPost(
            post = PostModel(
                username = currentUser.value,
                userImage = currentUserImage.intValue,
                postContent = postContent,
                dateAdded = currentDate.value
            )
        )
    }

    fun readAllPosts(context: Context): MutableList<PostModel> {
        val listOfPosts = mutableListOf<PostModel>()

        try {
            val db = DBHandler(context)

            listOfPosts.clear()
            listOfPosts.addAll(db.readPosts())
            listOfPosts.reverse()
        } catch (ex: Exception) {
            Log.d("db", "Error reading: ${ex.message}")
        }
        return listOfPosts
    }
}