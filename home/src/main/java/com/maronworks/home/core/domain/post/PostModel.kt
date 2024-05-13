package com.maronworks.home.core.domain.post

import androidx.annotation.DrawableRes

/*
 *  [Params]
 *      username: who post
 *
 */
data class PostModel(
    val username: String,
    @DrawableRes val userImage: Int,
    val postContent: String,
    val dateAdded: String
)