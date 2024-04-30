package com.maronworks.postapplication.home.domain.screen

sealed class HomeNavScreen(val route: String) {
    data object Home : HomeNavScreen("home")
    data object NewPost : HomeNavScreen("new_post")
    data object Profile : HomeNavScreen("profile")
}