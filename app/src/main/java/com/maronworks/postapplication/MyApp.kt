package com.maronworks.postapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.maronworks.postapplication.core.navigation.NavGraphSetup
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

@Composable
fun MyApp() {
    val navController = rememberNavController()

    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavGraphSetup(navController = navController)
        }
    }
}