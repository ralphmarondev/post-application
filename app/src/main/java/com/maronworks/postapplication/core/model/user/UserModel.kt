package com.maronworks.postapplication.core.model.user

data class UserModel(
    val fullName: String,
    val username: String,
    val password: String = ""
)