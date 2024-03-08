package com.maronworks.postapplication.mainf.domain.model.newpost

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.maronworks.postapplication.core.db.DBHandler

class NewPostViewModel : ViewModel() {
    var newPost = mutableStateOf("")
    private var userCreated = mutableStateOf("")
    private var datePosted = mutableStateOf("")

    fun addNewPost(context: Context) {
        try {
            val db = DBHandler(context)
            userCreated.value = db.readCurrentUser()
            datePosted.value = "2024-03-08" //getDateTime()

            db.addPost(
                userCreated = userCreated.value,
                postContent = newPost.value,
                datePosted = datePosted.value
            )
        } catch (e: Exception) {
            Log.d("adding post", "Error: ${e.message}")
        }
    }

}