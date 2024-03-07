package com.maronworks.postapplication.mainf.domain.model.mainf

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class MainFBottomBarItem(
    val label: String,
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector
)

val mainFBottomBarItems = listOf(
    MainFBottomBarItem(
        label = "Home",
        defaultIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    ),
    MainFBottomBarItem(
        label = "New",
        defaultIcon = Icons.Outlined.Add,
        selectedIcon = Icons.Filled.Add
    ),
    MainFBottomBarItem(
        label = "Profile",
        defaultIcon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle
    )
)