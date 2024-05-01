package com.maronworks.postapplication.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maronworks.postapplication.MainViewModel
import com.maronworks.postapplication.authentication.LoginSignUpActivity
import com.maronworks.postapplication.core.local.preferences.SharedPreferenceManager
import com.maronworks.postapplication.home.HomeActivity
import com.maronworks.postapplication.onboarding.OnBoardingActivity

@Composable
fun AppNavigation(
    context: Context = LocalContext.current,
    navController: NavHostController = rememberNavController(),
) {
    val preference = SharedPreferenceManager(context)
    val startDestination =
        if (preference.isFirstLaunch()) Screen.OnBoarding.route else Screen.Auth.route
    val mainVM = MainViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.OnBoarding.route) {
            OnBoardingActivity(
                onGetStarted = {
                    preference.setOnBoardingCompleted()
                    navController.popBackStack()
                    navController.navigate(Screen.Auth.route)
                }
            )
        }
        composable(Screen.Auth.route) {
            LoginSignUpActivity(
                mainVM = mainVM,
                onLogin = {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(Screen.Home.route) {
            HomeActivity(mainVM = mainVM)
        }
    }
}