package com.maronworks.postapplication.core.navigation

sealed class Screens(val route: String){
    data object Splash: Screens("splash")
    data object Auth: Screens("authentication")
    data object MainF: Screens("main_features")
}