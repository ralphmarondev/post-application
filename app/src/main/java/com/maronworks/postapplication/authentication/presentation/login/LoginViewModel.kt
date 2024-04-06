package com.maronworks.postapplication.authentication.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    var username = mutableStateOf("")
    var password = mutableStateOf("")

    var showPassword = mutableStateOf(false)

    fun toggleShowPassword(){
        showPassword.value = !showPassword.value
    }
}