package com.maronworks.postapplication.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.maronworks.postapplication.MainViewModel
import com.maronworks.postapplication.R
import com.maronworks.postapplication.core.util.debugLog
import com.maronworks.postapplication.home.data.local.db.DBHandler
import com.maronworks.postapplication.home.domain.post.PostModel
import com.maronworks.postapplication.home.util.getCurrentDateAndTime

@SuppressLint("StaticFieldLeak")
class HomeViewModel(private val context: Context, mainVM: MainViewModel) : ViewModel() {
    var isDarkTheme = mutableStateOf(false)
    var currentUser = mutableStateOf(mainVM.currentUser.value)
    var currentUserPicture = mutableIntStateOf(R.drawable.sample_image)

    fun onDarkTheme() {
        isDarkTheme.value = !isDarkTheme.value

        // TODO: change the theme
        debugLog("Current user: ${currentUser.value}")
    }

    fun setCurrentUser(user: String) {
        currentUser.value = user
    }

    fun onPost(postContent: String) {
        val post = PostModel(
            ownerPicture = currentUserPicture.intValue,
            ownerName = currentUser.value,
            postContent = postContent,
            datePosted = getCurrentDateAndTime()
        )
        debugLog("username: ${post.ownerName},\ncontent: ${post.postContent},\ndatePosted: ${post.datePosted}")

        try {
            val db = DBHandler(context)

            db.createPost(post)
            debugLog("Success [create-post]: Saved successfully!")
        } catch (ex: Exception) {
            debugLog("Error [create-post]: ${ex.message}")
        }
    }

    fun getAllPost(): MutableList<PostModel> {
        val listOfPosts = mutableListOf<PostModel>()

        try {
            val db = DBHandler(context)

            listOfPosts.clear()
            listOfPosts.addAll(db.readPosts())
            listOfPosts.reverse()

            debugLog("Success [reading-post]: posts count: ${listOfPosts.size}.")
        } catch (ex: Exception) {
            debugLog("Error [reading-post]: ${ex.message}")
        }
        return listOfPosts
    }
}