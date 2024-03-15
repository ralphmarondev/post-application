package com.maronworks.postapplication.auth.presentation.drawer.help

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel

class HelpViewModel : ViewModel() {
    private var expanded = mutableStateOf(false)
    fun expandCard() {
        expanded.value = !expanded.value
    }

    fun getCardIcon(): ImageVector {
        return if (expanded.value) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown
    }

    fun isExpanded():Boolean{
        return expanded.value
    }
}