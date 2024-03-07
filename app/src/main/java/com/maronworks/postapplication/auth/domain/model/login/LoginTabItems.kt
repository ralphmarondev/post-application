package com.maronworks.postapplication.auth.domain.model.login

import com.maronworks.postapplication.R

data class LoginTabItems(
    val label: String,
    val icon: Int
)

val loginTabItems = listOf(
    LoginTabItems(
        label = "LOGIN",
        icon = R.drawable.login
    ),
    LoginTabItems(
        label = "REGISTER",
        icon = R.drawable.register
    )
)