package com.maronworks.postapplication.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

@Composable
fun MyApp() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavGraphSetup()
        }
    }
}