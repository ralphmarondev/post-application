package com.maronworks.postapplication.auth.domain.model.drawer

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class DrawerViewModel : ViewModel() {
    companion object DrawerScreens {
        const val DEFAULT = -1
    }

    private var screen = mutableIntStateOf(DEFAULT)

    fun setScreen(index: Int) {
        screen.intValue = index
    }

    fun getScreen(): Int {
        return screen.intValue
    }

    fun resetScreen() {
        screen.intValue = DEFAULT
    }
}