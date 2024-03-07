package com.maronworks.postapplication.mainf.presentation.mainf

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.maronworks.postapplication.mainf.domain.model.mainf.MainFViewModel
import com.maronworks.postapplication.mainf.domain.model.mainf.mainFBottomBarItems
import com.maronworks.postapplication.mainf.presentation.home.HomeScreen
import com.maronworks.postapplication.mainf.presentation.newpost.NewPostScreen
import com.maronworks.postapplication.mainf.presentation.profile.ProfileScreen

private val vm = MainFViewModel()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                mainFBottomBarItems.forEachIndexed { index, item ->
                    NavigationBarItem(selected = vm.selectedIndex.intValue == index,
                        onClick = {
                            vm.onChangeIndex(index)
                        },
                        icon = {
                            Icon(
                                imageVector = if (vm.selectedIndex.intValue == index)
                                    item.selectedIcon else item.defaultIcon,
                                contentDescription = item.label,
                                tint = if (vm.selectedIndex.intValue == index)
                                    MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.secondary
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = if (vm.selectedIndex.intValue == index)
                                    FontWeight.W600 else FontWeight.W500,
                                fontSize = if (vm.selectedIndex.intValue == index)
                                    14.sp else 12.sp,
                                color = if (vm.selectedIndex.intValue == index)
                                    MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.secondary
                            )
                        }
                    )
                }
            }
        }
    ) {
        Box {
            when (vm.selectedIndex.intValue) {
                0 -> HomeScreen(navController = navController)
                1 -> NewPostScreen(onPost = { vm.goBackHome() }, onClose = { vm.goBackHome() })
                2 -> ProfileScreen()
            }
        }
    }
}