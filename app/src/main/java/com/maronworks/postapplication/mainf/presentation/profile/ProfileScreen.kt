package com.maronworks.postapplication.mainf.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.R
import com.maronworks.postapplication.mainf.domain.model.profile.ProfileTabRowModel
import com.maronworks.postapplication.mainf.domain.model.profile.ProfileViewModel
import com.maronworks.postapplication.mainf.presentation.profile.component.PhotosList
import com.maronworks.postapplication.mainf.presentation.profile.component.PostList
import com.maronworks.postapplication.mainf.presentation.profile.component.VideosList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val vm = ProfileViewModel()
    val context = LocalContext.current
    val tabRowItems = listOf(
        ProfileTabRowModel(
            icon = R.drawable.posts
        ),
        ProfileTabRowModel(
            icon = R.drawable.videos
        ),
        ProfileTabRowModel(
            icon = R.drawable.account
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = vm.getUsername(context = context),
                        fontFamily = FontFamily.Monospace
                    )
                },
                actions = {
                    IconButton(
                        onClick = { vm.onShowBottomSheetClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
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
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = vm.getFullName(context),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "@${vm.getUsername(context)}",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Divider(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))

                TabRow(selectedTabIndex = vm.getSelectedTabIndex()) {
                    tabRowItems.forEachIndexed { index, item ->
                        Tab(
                            selected = vm.isSelected(index),
                            onClick = {
                                vm.setSelectedTabIndex(index)
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = "",
                                    tint = vm.iconTint(index = index)
                                )
                            }
                        )
                    }
                }

                when (vm.getSelectedTabIndex()) {
                    0 -> PostList(
                        vm = vm,
                        vm.getAllUserPosts(context)
                    )

                    1 -> VideosList()
                    2 -> PhotosList()
                }
            }

            // show bottom sheet
            if (vm.bottomSheetState()) {
                ModalBottomSheet(
                    onDismissRequest = { vm.toggleShowBottomSheet() },
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .defaultMinSize(minHeight = 500.dp)
                    ) {
                        ElevatedButton(
                            onClick = {
                                vm.onUpdateProfile()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ) {
                            Text(
                                text = "Edit Profile",
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        ElevatedButton(
                            onClick = {
                                vm.onDeleteProfile()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ) {
                            Text(
                                text = "Delete Profile",
                                fontFamily = FontFamily.Monospace,
                                color = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}