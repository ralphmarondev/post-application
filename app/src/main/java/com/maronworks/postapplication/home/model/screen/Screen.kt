package com.maronworks.postapplication.home.model.screen

sealed class Screen(val route: String) {
    data object Feed: Screen("feed")
    data object NewPost: Screen("new_post")
    data object Profile: Screen("profile")
}