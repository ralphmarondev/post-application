package com.maronworks.postapplication.login.presentation.components

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    ElevatedButton(onClick = onClick) {
        Text(text = text)
    }
}