package com.maronworks.postapplication.auth.presentation.drawer.settings

import androidx.annotation.DrawableRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.maronworks.postapplication.R


// TODO: Implement this
class SettingsViewModel : ViewModel() {
    private var onDarkTheme = mutableStateOf(false)
    fun onDarkTheme() {
        onDarkTheme.value = !onDarkTheme.value
    }

    fun darkThemeIcon(): Int {
        return if (onDarkTheme.value) R.drawable.ic_darkmode else R.drawable.ic_lightmode
    }

    fun onExportDatabase() {

    }

    fun onResetToDefault() {

    }
}