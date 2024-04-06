package com.maronworks.postapplication.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {
    Text(
        text = "Home Screen",
        modifier = Modifier
            .padding(15.dp)
            .clickable {
                onLogout()
            }
    )
}