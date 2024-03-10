package com.maronworks.postapplication.mainf.presentation.profile.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun VideosList() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 300.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No Videos yet.",
            fontFamily = FontFamily.Monospace
        )
    }
}