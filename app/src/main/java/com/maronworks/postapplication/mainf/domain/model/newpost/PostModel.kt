package com.maronworks.postapplication.mainf.domain.model.newpost

data class PostModel(
    val id: Int = -1,
    val userCreated: String,
    val postContent: String,
    val datePosted: String
)