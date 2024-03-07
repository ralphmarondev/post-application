package com.maronworks.postapplication.auth.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.maronworks.postapplication.auth.domain.model.login.LoginViewModel
import com.maronworks.postapplication.auth.domain.model.login.loginTabItems
import com.maronworks.postapplication.auth.presentation.login.components.Login
import com.maronworks.postapplication.auth.presentation.login.components.Register

private val vm = LoginViewModel()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavHostController
) {
    Scaffold {
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
