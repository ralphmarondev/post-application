package com.maronworks.postapplication.auth.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.maronworks.postapplication.auth.domain.model.drawer.DrawerViewModel
import com.maronworks.postapplication.auth.domain.model.login.LoginViewModel
import com.maronworks.postapplication.auth.domain.model.login.loginTabItems
import com.maronworks.postapplication.auth.presentation.drawer.about.About
import com.maronworks.postapplication.auth.presentation.drawer.feedback.Feedback
import com.maronworks.postapplication.auth.presentation.drawer.help.Help
import com.maronworks.postapplication.auth.presentation.drawer.settings.Settings
import com.maronworks.postapplication.auth.presentation.drawer.sourcecode.SourceCode
import com.maronworks.postapplication.auth.presentation.login.components.Login
import com.maronworks.postapplication.auth.presentation.login.components.Register
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


private val drawerModel = DrawerViewModel()

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavHostController
) {
    val vm = LoginViewModel()
    val scope = rememberCoroutineScope()
    val state = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(scope, state)
        },
        drawerState = state
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { /*TODO*/ },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    state.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Menu,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(Int.MAX_VALUE.dp)
                        .padding(10.dp)
                ) {
                    Column {
                        TabRow(
                            selectedTabIndex = vm.selectedTab.intValue,
                            modifier = Modifier
                                .padding(8.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            loginTabItems.forEachIndexed { index, item ->
                                Tab(
                                    selected = vm.selectedTab.intValue == index,
                                    onClick = { vm.onChangeTab(index) },
                                    text = {
                                        Text(
                                            text = item.label,
                                            fontFamily = FontFamily.Monospace
                                        )
                                    },
                                    icon = {
                                        Image(
                                            painter = painterResource(id = item.icon),
                                            contentDescription = item.label,
                                            colorFilter = ColorFilter.tint(
                                                if (vm.selectedTab.intValue == index)
                                                    MaterialTheme.colorScheme.primary
                                                else MaterialTheme.colorScheme.tertiary
                                            )
                                        )
                                    },
                                    selectedContentColor = MaterialTheme.colorScheme.primary,
                                    unselectedContentColor = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }

                        when (vm.selectedTab.intValue) {
                            0 -> Login(navController = navController)
                            1 -> Register(onRegister = { vm.onChangeTab(0) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DrawerContent(
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    ModalDrawerSheet {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(MaterialTheme.colorScheme.tertiary),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(
                    text = "Developed by:",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
                Text(
                    text = "Ralph Maron Eda",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
        Column {
            val drawerItem = listOf(
                NavDrawerItem(
                    label = "Settings",
                    icon = Icons.Outlined.Settings,
                    onClick = {
                    },
                    badge = "1"
                ),
                NavDrawerItem(
                    label = "Help",
                    icon = Icons.Outlined.Info,
                    onClick = {
                    },
                    badge = ""
                ),
                NavDrawerItem(
                    label = "About",
                    icon = Icons.Outlined.Star,
                    onClick = {
                    },
                    badge = ""
                ),
                NavDrawerItem(
                    label = "Source Code",
                    icon = Icons.Outlined.Create,
                    onClick = {
                    },
                    badge = ""
                ),
                NavDrawerItem(
                    label = "Feedback",
                    icon = Icons.Outlined.MailOutline,
                    onClick = {
                    },
                    badge = ""
                )
            )
            drawerItem.forEachIndexed { index, item ->
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = item.label,
                            fontFamily = FontFamily.Monospace
                        )
                    },
                    selected = false,
                    onClick = {
                        drawerModel.setScreen(index)
                        item.onClick()
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
//                    badge = {
//                        if (item.badge.isEmpty()) {
//                            Log.d("badge", "No badge")
//                        } else {
//                            Badge(
//                                content = {
//                                    Text(
//                                        text = item.badge,
//                                        fontFamily = FontFamily.Monospace
//                                    )
//                                }
//                            )
//                        }
//                    },
                    modifier = Modifier
                        .padding(5.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            NavigationDrawerItem(
                label = {
                    Text(
                        text = "Close Drawer",
                        fontFamily = FontFamily.Monospace
                    )
                },
                selected = false,
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            close()
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
    when (drawerModel.getScreen()) {
        DrawerScreens.SETTINGS -> {
            Settings(
                onBack = {
                    drawerModel.resetScreen()
                }
            )
        }

        DrawerScreens.HELP -> {
            Help(
                onBack = {
                    drawerModel.resetScreen()
                }
            )
        }

        DrawerScreens.ABOUT -> {
            About(
                onBack = {
                    drawerModel.resetScreen()
                }
            )
        }

        DrawerScreens.SOURCE -> {
            SourceCode(
                onBack = {
                    drawerModel.resetScreen()
                }
            )
        }

        DrawerScreens.FEEDBACK -> {
            Feedback(
                onBack = {
                    drawerModel.resetScreen()
                }
            )
        }
    }
}

private object DrawerScreens {
    const val SETTINGS = 0
    const val HELP = 1
    const val ABOUT = 2
    const val SOURCE = 3
    const val FEEDBACK = 4
}

private data class NavDrawerItem(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit,
    val badge: String
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginPrev() {
    LoginScreen(navController = rememberNavController())
}
