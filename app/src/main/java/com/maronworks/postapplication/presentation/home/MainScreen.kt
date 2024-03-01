package com.maronworks.postapplication.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomBarItem.forEachIndexed { index, item ->
                    NavigationBarItem(selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(
                                imageVector = if (selectedIndex == index) item.selectedIcon else item.defaultIcon,
                                contentDescription = item.label
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    )
                }
            }
        }
    ) {
        Box {
            when (selectedIndex) {
                0 -> HomeScreen(navController = navController)
                1 -> AddScreen()
                2 -> ProfileScreen()

            }
        }
    }
}

private data class BottomBarItem(
    val label: String,
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector
)

private val bottomBarItem = listOf(
    BottomBarItem(
        label = "Home",
        defaultIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    ),
    BottomBarItem(
        label = "Add",
        defaultIcon = Icons.Outlined.Add,
        selectedIcon = Icons.Filled.Add
    ),
    BottomBarItem(
        label = "Home",
        defaultIcon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle
    )
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPrev() {
    MainScreen(navController = rememberNavController())
}