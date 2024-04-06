package com.maronworks.postapplication.core.model.screen

sealed class Screen(val route: String){
    data object Authentication: Screen("auth")
    data object Home: Screen("home")
}