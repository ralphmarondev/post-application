package com.maronworks.authentication.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.authentication.presentation.login.Login
import com.maronworks.authentication.presentation.register.Register
import com.maronworks.authentication.util.TabIndex

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    onLogin: () -> Unit
) {
    var isDarkTheme by remember {
        mutableStateOf(false)
    }
    val icon = if (isDarkTheme) Icons.Outlined.LightMode else Icons.Outlined.DarkMode

    var selectedTabIndex by remember { mutableIntStateOf(TabIndex.LOGIN) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    val viewModel = LoginViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Authentication")
                },
                actions = {
                    IconButton(onClick = { isDarkTheme = !isDarkTheme }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 300.dp)
            ) {
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                ) {
                    val listOfTabs = listOf("Login", "Register")

                    listOfTabs.forEachIndexed { index, s ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index }
                        ) {
                            Text(
                                text = s,
                                modifier = Modifier
                                    .padding(vertical = 10.dp),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W600,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                }

                when (selectedTabIndex) {
                    TabIndex.LOGIN -> {
                        Login(
                            modifier = Modifier
                                .padding(horizontal = 15.dp),
                            context = context,
                            scope = scope,
                            viewModel = viewModel,
                            snackBarState = snackBarState,
                            onLogin = { onLogin() }
                        )
                    }

                    TabIndex.REGISTER -> {
                        Register(
                            modifier = Modifier
                                .padding(horizontal = 15.dp),
                            context = context,
                            scope = scope,
                            viewModel = viewModel,
                            snackBarState = snackBarState,
                            onRegister = {
                                selectedTabIndex = TabIndex.LOGIN
                            }
                        )
                    }
                }
            }
        }
    }
}
