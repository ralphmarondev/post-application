package com.maronworks.postapplication.navigation.model

sealed class Screen(val route: String) {
    data object OnBoarding : Screen("on_boarding")
    data object Login : Screen("login")
    data object Home : Screen("home")
}