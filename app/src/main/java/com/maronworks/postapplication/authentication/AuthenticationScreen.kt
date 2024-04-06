package com.maronworks.postapplication.authentication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maronworks.postapplication.authentication.presentation.login.Login
import com.maronworks.postapplication.authentication.presentation.register.Register


private sealed class AuthScreen(val route: String) {
    data object Login : AuthScreen("login")
    data object Register : AuthScreen("register")
}

@Composable
fun AuthenticationScreen(
    onLogin: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AuthScreen.Login.route
    ) {
        composable(AuthScreen.Login.route) {
            Login(
                onLogin = onLogin,
                onRegister = {
                    navController.navigate(AuthScreen.Register.route)
                }
            )
        }
        composable(AuthScreen.Register.route) {
            Register(onBack = { navController.popBackStack() })
        }
    }
}