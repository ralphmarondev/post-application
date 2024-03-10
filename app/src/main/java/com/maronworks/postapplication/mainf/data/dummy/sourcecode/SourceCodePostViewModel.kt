package com.maronworks.postapplication.mainf.data.dummy.sourcecode

import android.content.Context
import android.content.Intent
import android.net.Uri
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

class SourceCodePostViewModel : ViewModel() {
    // region ICONS
    private var comment = mutableStateOf(false)
    private var favorite = mutableStateOf(false)
    private var repost = mutableStateOf(false)
    private var share = mutableStateOf(false)
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
        return if (favorite.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
    }

    fun repostIcon(): ImageVector {
        return if (repost.value) Icons.Filled.AccountBox else Icons.Outlined.AccountBox
    }

    fun shareIcon(): ImageVector {
        return if (share.value) Icons.Filled.Share else Icons.Outlined.Share
    }
    // endregion ICONS

    fun visitSourceCode(context: Context) {
        val url = "https://github.com/ralphmarondev/post-application/"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent) // opens the default browser
    }
}