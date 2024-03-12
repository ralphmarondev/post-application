package com.maronworks.postapplication.core.domain.model.user

data class UserModel(
    val fullName: String,
    val username: String,
    val password: String = ""
)