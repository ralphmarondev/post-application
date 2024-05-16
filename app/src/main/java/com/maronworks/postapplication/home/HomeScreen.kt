package com.maronworks.postapplication.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maronworks.postapplication.core.data.preferences.SharedPreferencesManager
import com.maronworks.postapplication.home.feed.Feed
import com.maronworks.postapplication.home.model.nav_icon.NavIconModel
import com.maronworks.postapplication.home.model.screen.Screen
import com.maronworks.postapplication.home.newpost.NewPost
import com.maronworks.postapplication.home.profile.Profile
import com.maronworks.postapplication.home.util.BottomBarIndex
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    context: Context = LocalContext.current,
    pref: SharedPreferencesManager,
    onLogout: () -> Unit
) {
    val viewModel = HomeViewModel(pref)
    val navController = rememberNavController()
    var selectedIndex by remember { mutableIntStateOf(BottomBarIndex.FEED) }

    Log.d("pref", "Current user: ${pref.getCurrentUser()}")
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            val listOfIcons = listOf(
                NavIconModel(
                    defaultIcon = Icons.Outlined.Home,
                    selectedIcon = Icons.Filled.Home
                ),
                NavIconModel(
                    defaultIcon = Icons.Outlined.PostAdd,
                    selectedIcon = Icons.Filled.PostAdd
                ),
                NavIconModel(
                    defaultIcon = Icons.Outlined.AccountCircle,
                    selectedIcon = Icons.Filled.AccountCircle
                )
            )
            NavigationBar {
                listOfIcons.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            when (selectedIndex) {
                                BottomBarIndex.FEED -> {
                                    navController.popBackStack()
                                    navController.navigate(Screen.Feed.route)
                                }

                                BottomBarIndex.NEW_POST -> {
                                    navController.popBackStack()
                                    navController.navigate(Screen.NewPost.route)
                                }

                                BottomBarIndex.PROFILE -> {
                                    navController.popBackStack()
                                    navController.navigate(Screen.Profile.route)
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (selectedIndex == index) item.selectedIcon else item.defaultIcon,
                                contentDescription = ""
                            )
                        }
                    )
                }

            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Feed.route,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(Screen.Feed.route) {
                Feed(
                    currentUser = pref.getCurrentUser(),
                    posts = viewModel.readAllPosts(context)
                )
            }
            composable(Screen.NewPost.route) {
                NewPost(
                    viewModel = viewModel,
                    onBack = {
                        selectedIndex = BottomBarIndex.FEED
                        navController.popBackStack()
                        navController.navigate(Screen.Feed.route)
                    }
                )
            }
            composable(Screen.Profile.route) {
                Profile(
                    pref = pref,
                    onLogout = onLogout
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                pref = SharedPreferencesManager(LocalContext.current),
                onLogout = {}
            )
        }
    }
}