package com.maronworks.postapplication.home.model.nav_icon

import androidx.compose.ui.graphics.vector.ImageVector

data class NavIconModel(
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector,
    val label: String = ""
)