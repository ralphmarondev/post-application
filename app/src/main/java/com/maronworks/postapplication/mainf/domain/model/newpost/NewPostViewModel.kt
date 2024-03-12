package com.maronworks.postapplication.mainf.domain.model.newpost

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.maronworks.postapplication.core.data.db.DBHandler
import com.maronworks.postapplication.mainf.domain.util.getCurrentDateTime

class NewPostViewModel : ViewModel() {
    var newPost = mutableStateOf("")
    private var userCreated = mutableStateOf("")
    private var datePosted = mutableStateOf("")

    fun addNewPost(context: Context) {
        try {
            val db = DBHandler(context)
            userCreated.value = db.readCurrentUser()
            datePosted.value = getCurrentDateTime()

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