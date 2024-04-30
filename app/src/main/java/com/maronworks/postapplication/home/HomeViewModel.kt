package com.maronworks.postapplication.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

@SuppressLint("StaticFieldLeak")
class HomeViewModel(private val context: Context) : ViewModel() {
    var isDarkTheme = mutableStateOf(false)

    fun onDarkTheme(){
        isDarkTheme.value = !isDarkTheme.value

        // TODO: change the theme
    }

    fun onPost() {

    }
}