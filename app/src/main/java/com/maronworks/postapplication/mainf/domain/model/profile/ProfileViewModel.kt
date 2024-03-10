package com.maronworks.postapplication.mainf.domain.model.profile

import android.content.Context
import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.maronworks.postapplication.core.db.DBHandler
import com.maronworks.postapplication.mainf.domain.model.newpost.PostModel

class ProfileViewModel : ViewModel() {
    private var fullName = mutableStateOf("")
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

    fun getFullName(context: Context): String {
        return try {
            val db = DBHandler(context)

            fullName.value = db.getCurrentUserDetails(username.value).fullName
            fullName.value // return this
        } catch (e: Exception) {
            Log.d("reading current user", "Error: ${e.message}")
            "Error [Full Name]!" // else return this
        }
    }

    // tab-row
    private val selectedTabIndex = mutableIntStateOf(0)
    fun getSelectedTabIndex(): Int {
        return selectedTabIndex.intValue
    }

    fun setSelectedTabIndex(index: Int) {
        selectedTabIndex.intValue = index
    }

    fun isSelected(index: Int): Boolean {
        return selectedTabIndex.intValue == index
    }

    @Composable
    fun iconTint(index: Int): Color {
        return if (isSelected(index))
            MaterialTheme.colorScheme.primary
        else
            MaterialTheme.colorScheme.secondary

    }

    // dialog
    private var dialogState = mutableStateOf(false)

    fun getDialogState(): Boolean {
        return dialogState.value
    }

    fun showDialog() {
        dialogState.value = true
    }

    fun closeDialog() {
        dialogState.value = false
    }

    // posts
    fun getAllUserPosts(context: Context): MutableList<PostModel> {
        val items = mutableListOf<PostModel>()

        try {
            val db = DBHandler(context)

            items.clear()
            items.addAll(db.readPostWhereUser(username.value))
            items.reverse()
            Log.d("getting all post", "Successfully")
            return items
        } catch (e: Exception) {
            Log.d("getting all post", "Error: ${e.message}")
        }

        return items
    }

    fun deletePost(
        context: Context,
        post: PostModel
    ) {
        try {
            val db = DBHandler(context)

            db.deletePost(id = post.id, userCreated = post.userCreated)
            Log.d("delete post value", "Id: ${post.id}, UserCreated: ${post.userCreated}")
            Log.d("delete post", "Deleted successfully!")
        } catch (e: Exception) {
            Log.d("delete post", "Error: ${e.message}")
        }
    }

    // editing the current user on profile screen
    private var showBottomSheet = mutableStateOf(false)

    fun bottomSheetState(): Boolean {
        return showBottomSheet.value
    }

    fun onShowBottomSheetClick() {
        showBottomSheet.value = true
    }

    fun toggleShowBottomSheet() {
        showBottomSheet.value = false
    }

    fun onUpdateProfile() {
        // TODO: Implement this!
        Log.d("update profile", "Updating profile...")
    }

    fun onDeleteProfile() {
        // TODO: Implement this!
        Log.d("delete profile", "Deleting profile...")
    }
}