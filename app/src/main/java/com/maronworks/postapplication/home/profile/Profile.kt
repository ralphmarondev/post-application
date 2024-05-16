package com.maronworks.postapplication.home.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.R
import com.maronworks.postapplication.core.data.preferences.SharedPreferencesManager
import com.maronworks.postapplication.home.profile.components.CreateBottomSheet
import com.maronworks.postapplication.home.profile.components.SettingsBottomSheet
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile(
    pref: SharedPreferencesManager,
    onLogout: () -> Unit
) {
    var showCreateBottomSheet by remember { mutableStateOf(false) }
    var showSettingsBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = pref.getCurrentUser())
                },
                actions = {
                    IconButton(onClick = { showCreateBottomSheet = !showCreateBottomSheet }) {
                        Icon(
                            imageVector = Icons.Outlined.AddBox,
                            contentDescription = ""
                        )
                    }

                    IconButton(onClick = { showSettingsBottomSheet = !showSettingsBottomSheet }) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(vertical = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cute_me),
                        contentDescription = "",
                        modifier = Modifier
                            .border(
                                width = 4.dp,
                                brush = Brush.linearGradient(
                                    listOf(
                                        MaterialTheme.colorScheme.primary,
                                        Color.Magenta
                                    )
                                ),
                                shape = CircleShape
                            )
                            .size(110.dp)
                            .padding(6.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = "@${pref.getCurrentUser()}",
                        fontWeight = FontWeight.W500,
                        fontSize = 18.sp
                    )
                }
            }
        }

        if (showCreateBottomSheet) {
            CreateBottomSheet(
                onDismiss = {
                    showCreateBottomSheet = !showCreateBottomSheet
                }
            )
        }

        if (showSettingsBottomSheet) {
            SettingsBottomSheet(
                onDismiss = {
                    showSettingsBottomSheet = !showSettingsBottomSheet
                },
                onLogout = onLogout
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfilePreview() {
    PostApplicationTheme {
        Profile(pref = SharedPreferencesManager(LocalContext.current), onLogout = {})
    }
}