package com.maronworks.postapplication.home.domain.navItem

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItemModel(
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector,
    val label: String,
    val route: String,
)