package com.maronworks.postapplication.authentication

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maronworks.postapplication.authentication.components.Login
import com.maronworks.postapplication.authentication.components.Register
import com.maronworks.postapplication.ui.theme.PostApplicationTheme
import kotlinx.coroutines.CoroutineScope


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginSignUpActivity(
    context: Context = LocalContext.current,
    scope: CoroutineScope = rememberCoroutineScope(),
    onLogin: () -> Unit,
) {
    val viewModel = LoginSignUpViewModel(context)

    val snackBarState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 300.dp)
                    .padding(15.dp)
            ) {
                when (viewModel.selectedScreen.intValue) {
                    0 -> Login(
                        onLogin = onLogin,
                        scope = scope,
                        snackbarHostState = snackBarState,
                        viewModel = viewModel
                    )

                    1 -> Register(
                        viewModel = viewModel,
                        onRegister = { viewModel.selectedScreen.intValue = 0 }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginSignUpPreview() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginSignUpActivity {

            }
        }
    }
}