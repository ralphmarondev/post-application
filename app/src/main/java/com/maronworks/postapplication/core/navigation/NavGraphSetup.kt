package com.maronworks.postapplication.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maronworks.postapplication.auth.presentation.login.LoginScreen
import com.maronworks.postapplication.core.presentation.splash.SplashScreen
import com.maronworks.postapplication.mainf.presentation.mainf.MainScreen

@Composable
fun NavGraphSetup(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(Screens.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screens.Auth.route) {
            LoginScreen(navController = navController)
        }
        composable(Screens.MainF.route) {
            MainScreen(navController = navController)
        }
    }
}