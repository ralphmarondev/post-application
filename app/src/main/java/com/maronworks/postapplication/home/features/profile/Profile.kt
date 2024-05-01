package com.maronworks.postapplication.home.features.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.MainViewModel
import com.maronworks.postapplication.R
import com.maronworks.postapplication.home.HomeViewModel
import com.maronworks.postapplication.home.features.profile.components.EditFullNameDialog
import com.maronworks.postapplication.home.features.profile.components.SettingsButton
import com.maronworks.postapplication.home.features.profile.domain.settings_button.SettingsButtonModel
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    mainVM: MainViewModel,
    viewModel: HomeViewModel,
) {
    var showEditFullNameDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        fontFamily = FontFamily.Monospace
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val settingButtons = listOf(
                SettingsButtonModel(
                    icon = Icons.Outlined.AccountCircle,
                    text = "Personal Information",
                    onClick = {}
                ),
                SettingsButtonModel(
                    icon = Icons.Outlined.PrivacyTip,
                    text = "Security and Privacy",
                    onClick = {}
                ),
                SettingsButtonModel(
                    icon = Icons.Outlined.Settings,
                    text = "Settings",
                    onClick = {}
                ),
                SettingsButtonModel(
                    icon = Icons.AutoMirrored.Outlined.Logout,
                    text = "Logout",
                    onClick = {}
                )
            )
            item {
                Box(
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sample_image),
                        contentDescription = "",
                        modifier = Modifier
                            .border(2.dp, Color.Magenta, CircleShape)
                            .size(85.dp)
                            .padding(4.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(bottom = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val fName =
                            mainVM.currentFullName.value.ifEmpty { "Full Name" }
                        Text(
                            text = fName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        IconButton(
                            onClick = {
                                showEditFullNameDialog = !showEditFullNameDialog
                            },
                            modifier = Modifier
                                .size(20.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.EditNote,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(18.dp)
                            )
                        }
                    }
                    Text(text = "@${viewModel.currentUser.value}")
                }
            }
            items(settingButtons.size) { index ->
                SettingsButton(
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    icon = settingButtons[index].icon,
                    text = settingButtons[index].text,
                    onClick = settingButtons[index].onClick
                )
            }
        }

        if (showEditFullNameDialog) {
            EditFullNameDialog(
                viewModel = mainVM,
                onDismiss = {
                    showEditFullNameDialog = false
                },
                onSave = {
                    // TODO: update database
                }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfilePreview() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Profile(
                mainVM = MainViewModel(),
                viewModel = HomeViewModel(LocalContext.current, MainViewModel())
            )
        }
    }
}
