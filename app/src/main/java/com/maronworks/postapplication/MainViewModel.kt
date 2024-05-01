package com.maronworks.postapplication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var currentUser= mutableStateOf("username")
    var currentFullName = mutableStateOf("Full Name")
}