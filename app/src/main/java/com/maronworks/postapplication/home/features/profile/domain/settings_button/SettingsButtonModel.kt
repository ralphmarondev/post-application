package com.maronworks.postapplication.home.features.profile.domain.settings_button

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsButtonModel(
    val icon: ImageVector,
    val text: String,
    val onClick: () -> Unit,
)
