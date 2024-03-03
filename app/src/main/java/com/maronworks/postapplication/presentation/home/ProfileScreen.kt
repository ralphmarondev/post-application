package com.maronworks.postapplication.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    currentUser: String
) {
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = currentUser, fontFamily = FontFamily.Monospace)
                },
                actions = {
                    IconButton(onClick = { }
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
                        .size(75.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "Full Name",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "@$currentUser",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Divider(modifier = Modifier.padding(10.dp))

                TabRow(selectedTabIndex = selectedIndex) {
                    tabRowItem.forEachIndexed { index, item ->
                        Tab(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label,
                                    tint = if (selectedIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                )
                            }
                        )
                    }
                }
                when (selectedIndex) {
                    0 -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .defaultMinSize(minHeight = 200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Developed by: Ralph Maron Eda",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.W600,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    1 -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .defaultMinSize(minHeight = 200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "He is a Computer Engineering Student",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.W600,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    2 -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .defaultMinSize(minHeight = 200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Likes Mobile Development, so as Web [REACT] and Game Development [GODOT]",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.W600,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

private data class TabRowModel(
    val label: String,
    val icon: ImageVector
)

private val tabRowItem = listOf(
    TabRowModel(
        label = "Grid",
        icon = Icons.Outlined.CheckCircle
    ),
    TabRowModel(
        label = "Grid",
        icon = Icons.Outlined.Warning
    ),
    TabRowModel(
        label = "Grid",
        icon = Icons.Outlined.AccountBox
    )
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfileScreenPrev() {
    ProfileScreen(currentUser = "cute_girl")
}