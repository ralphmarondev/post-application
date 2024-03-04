package com.maronworks.postapplication.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.maronworks.postapplication.data.DBHandler
import com.maronworks.postapplication.model.PostModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    var currentUser by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val db = DBHandler(context)

    try {
        currentUser = db.readCurrentUser()
        Log.d("reading current user", "Success! Value: $currentUser")
    } catch (e: Exception) {
        Log.d("reading current user", "Error: ${e.message}")
    }

    val itemsList = rememberSaveable { mutableListOf<PostModel>() }

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomBarItem.forEachIndexed { index, item ->
                    NavigationBarItem(selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(
                                imageVector = if (selectedIndex == index) item.selectedIcon else item.defaultIcon,
                                contentDescription = item.label,
                                tint = if (selectedIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = if (selectedIndex == index) FontWeight.W600 else FontWeight.W500,
                                fontSize = if (selectedIndex == index) 14.sp else 12.sp,
                                color = if (selectedIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                            )
                        }
                    )
                }
            }
        }
    ) {
        Box {
            when (selectedIndex) {
                0 -> {
                    try {
                        itemsList.clear()
                        itemsList.addAll(db.readPosts())
                        itemsList.reverse() // so the first post will be the last added
                        Log.d("reading posts", "Success!")
                    } catch (e: Exception) {
                        Log.d("reading posts", "Error: ${e.message}")
                    }
                    HomeScreen(
                        navController = navController,
                        itemsList = itemsList
                    )
                }

                1 -> AddScreen(onPostClick = { selectedIndex = 0 }) // back to home
                2 -> ProfileScreen(currentUser)

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
        label = "Profile",
        defaultIcon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle
    )
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPrev() {
    MainScreen(navController = rememberNavController())
}