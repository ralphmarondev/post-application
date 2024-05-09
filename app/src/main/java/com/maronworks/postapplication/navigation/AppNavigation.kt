package com.maronworks.postapplication.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maronworks.postapplication.login.LoginScreen
import com.maronworks.postapplication.navigation.data.local.preferences.SharedPreferencesManager
import com.maronworks.postapplication.navigation.model.Screen
import com.maronworks.postapplication.onboarding.OnBoardingScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) {
    val pref = SharedPreferencesManager(context)
    val startDestination = if (pref.isFirstLaunch()) Screen.OnBoarding.route else Screen.Login.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(
                onGetStarted = {
                    // save to preference
                    pref.setOnBoardingCompleted()

                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen()
        }
        composable(Screen.Home.route) {

        }
    }
}