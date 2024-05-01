package com.maronworks.postapplication.home.domain.user

import androidx.annotation.DrawableRes

data class UserModel(
    val userName: String,
    val password: String,
    @DrawableRes val profilePicture: Int,
    val fullName: String = "",
)