package com.maronworks.postapplication.home.core.domain.post

/*
 *  [Params]
 *      username: who post
 *
 */
data class PostModel(
    val id: Int = -1,
    val username: String,
    val userImage: Int,
    val postContent: String,
    val dateAdded: String
)