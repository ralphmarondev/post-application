package com.maronworks.postapplication.mainf.domain.model.home

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.maronworks.postapplication.core.data.db.DBHandler
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
    private var comment = mutableStateOf(false)
    private var favorite = mutableStateOf(false)
    private var repost = mutableStateOf(false)
    private var share = mutableStateOf(false)

    fun isExpanded(): Boolean {
        return expanded.value
    }

    fun onExpand() {
        expanded.value = !expanded.value
    }

    fun postContentMaxLines():Int{
        return if(expanded.value) Int.MAX_VALUE else 4
    }

    // post card icons
    fun onCommentClick() {
        comment.value = !comment.value
    }

    fun onFavoriteClick() {
        favorite.value = !favorite.value
    }

    fun onRepostClick() {
        repost.value = !repost.value
    }

    fun onShareClick() {
        share.value = !share.value
    }

    fun commentIcon(): ImageVector {
        return if (comment.value) Icons.Filled.Email else Icons.Outlined.Email
    }

    fun favoriteIcon(): ImageVector {
        return if (favorite.value)Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
    }

    fun repostIcon(): ImageVector {
        return if (repost.value) Icons.Filled.AccountBox else Icons.Outlined.AccountBox
    }

    fun shareIcon(): ImageVector {
        return if (share.value) Icons.Filled.Share else Icons.Outlined.Share
    }
}