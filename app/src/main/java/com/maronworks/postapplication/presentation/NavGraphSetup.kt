package com.maronworks.postapplication.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maronworks.postapplication.presentation.home.MainScreen
import com.maronworks.postapplication.presentation.login.LoginScreen

sealed class Screens(val route: String){
    data object Login: Screens("login")
    data object Main: Screens("main")
}

@Composable
fun NavGraphSetup(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ) {
        composable(Screens.Login.route){
            LoginScreen(navController = navController)
        }
        composable(Screens.Main.route){
            MainScreen(navController = navController)
        }
    }
}