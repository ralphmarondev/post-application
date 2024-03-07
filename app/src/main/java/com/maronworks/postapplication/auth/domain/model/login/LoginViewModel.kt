package com.maronworks.postapplication.auth.domain.model.login

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.maronworks.postapplication.core.navigation.Screens

class LoginViewModel : ViewModel() {
    var selectedTab = mutableIntStateOf(0)
    var username = mutableStateOf("")
    val password = mutableStateOf("")

    fun onChangeTab(tabIndex: Int) {
        selectedTab.intValue = tabIndex
    }

    fun onLogin(navController: NavHostController) {
        navController.navigate(Screens.MainF.route)
    }

    fun onRegister() {
        selectedTab.intValue = 0
    }
}