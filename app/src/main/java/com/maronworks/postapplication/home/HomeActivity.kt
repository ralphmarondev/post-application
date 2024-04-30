package com.maronworks.postapplication.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maronworks.postapplication.home.domain.navItem.NavItemModel
import com.maronworks.postapplication.home.domain.screen.HomeNavScreen
import com.maronworks.postapplication.home.features.home.Home
import com.maronworks.postapplication.home.features.new_post.NewPost
import com.maronworks.postapplication.home.features.profile.Profile

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeActivity(
    navController: NavHostController = rememberNavController(),
) {
    var selectedScreen by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            val listOfNavItems = listOf(
                NavItemModel(
                    defaultIcon = Icons.Outlined.Home,
                    selectedIcon = Icons.Filled.Home,
                    label = "Home",
                    route = HomeNavScreen.Home.route
                ),
                NavItemModel(
                    defaultIcon = Icons.Outlined.PostAdd,
                    selectedIcon = Icons.Filled.PostAdd,
                    label = "New Post",
                    route = HomeNavScreen.NewPost.route
                ),
                NavItemModel(
                    defaultIcon = Icons.Outlined.AccountBox,
                    selectedIcon = Icons.Filled.AccountBox,
                    label = "Account",
                    route = HomeNavScreen.Profile.route
                )
            )

            NavigationBar {
                listOfNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedScreen == index,
                        onClick = {
                            selectedScreen = index
//                            navController.popBackStack()
                            navController.navigate(item.route) {
                                popUpTo(HomeNavScreen.Home.route)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (selectedScreen == index) item.selectedIcon else item.defaultIcon,
                                contentDescription = item.label
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navController = navController,
            startDestination = HomeNavScreen.Home.route
        ) {
            composable(HomeNavScreen.Home.route) {
                Home()
            }
            composable(HomeNavScreen.NewPost.route) {
                NewPost()
            }
            composable(HomeNavScreen.Profile.route) {
                Profile()
            }
        }
    }
}