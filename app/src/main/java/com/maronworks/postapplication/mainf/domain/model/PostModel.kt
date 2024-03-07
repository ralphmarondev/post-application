package com.maronworks.postapplication.mainf.domain.model

data class PostModel(
    val id: Int = -1,
    val userCreated: String,
    val postContent: String,
    val datePosted: String
)