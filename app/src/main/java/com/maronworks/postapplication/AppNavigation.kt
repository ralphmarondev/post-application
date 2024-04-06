package com.maronworks.postapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maronworks.postapplication.authentication.AuthenticationScreen
import com.maronworks.postapplication.core.model.screen.Screen
import com.maronworks.postapplication.home.HomeScreen
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

@Preview
@Composable
private fun Default() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background

        ) {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Authentication.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Authentication.route) {
            AuthenticationScreen(
                onLogin = {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onLogout = {
                    navController.popBackStack()
                    navController.navigate(Screen.Authentication.route)
                }
            )
        }
    }
}