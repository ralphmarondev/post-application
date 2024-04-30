package com.maronworks.postapplication.navigation

sealed class Screen(val route: String) {
    data object OnBoarding : Screen("on_boarding")
    data object Auth : Screen("auth")
    data object Home : Screen("home")
}
