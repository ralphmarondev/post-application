package com.maronworks.postapplication.home.domain.post

import androidx.annotation.DrawableRes

data class PostModel(
    @DrawableRes val ownerPicture: Int,
    val ownerName: String,
    val postContent: String,
    val datePosted: String,
)