package com.maronworks.postapplication.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.maronworks.postapplication.components.ProfilePostCard
import com.maronworks.postapplication.model.PostModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    currentUser: String,
    postsList: MutableList<PostModel>
) {
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(1)
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
                    text = "@$currentUser", //"@${readFromFile()}",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Divider(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))

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
                    0 -> SecondTab()
                    1 -> ListOfPostsTab(posts = postsList)
                    2 -> ThirdTab()
                }
            }
        }
    }
}

@Composable
fun ListOfPostsTab(posts: MutableList<PostModel>) {
    if (posts.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Not post yet. Go and create one!",
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
//            .verticalScroll(rememberScrollState())
                .padding(start = 5.dp, end = 5.dp, top = 15.dp, bottom = 15.dp)
        ) {
            posts.forEachIndexed { index, _ ->
                ProfilePostCard(item = posts[index])
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
private fun SecondTab() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .defaultMinSize(minHeight = 200.dp),
        contentAlignment = Alignment.Center
    ) {
        val options = listOf(
            "Developed by: Ralph Maron Eda",
            "He is a Computer Engineering Student",
            "Likes Mobile Development, so as Web [REACT] and Game Development [GODOT]"
        )
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }

        Column {
            Text(
                text = "Selected:\n$selectedOptionText",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable {
                        expanded = true
                    }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { selectedOption ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = selectedOption,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.W600,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.secondary,
                                textAlign = TextAlign.Center
                            )
                        },
                        onClick = {
                            selectedOptionText = selectedOption
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ThirdTab() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .defaultMinSize(minHeight = 200.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Coming Soon...",
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
    val postLists = remember { mutableListOf<PostModel>() }
    val items = mutableListOf(
        PostModel(
            userCreated = "ralphmaron",
            label = "Ralph Maron Eda is a computer engineering student who is passionate about learning" +
                    "different languages and technologies.",
            datePosted = "2024-03-02 at 2:04AM"
        ),
        PostModel(
            userCreated = "hello world",
            label = "Hello there, always be happy ok!",
            datePosted = "2024-03-03 at 10:43AM"
        ),
        PostModel(
            userCreated = "cazmir",
            label = "Why so cute!",
            datePosted = "2024-03-03 at 11:23AM"
        ),
        PostModel(
            userCreated = "android",
            label = "This is my new version, Iguana version! Isn't it amazing? I have a gradient on my upper left lol.",
            datePosted = "2024-03-03 at 10:24AM"
        )
    )
    postLists.clear()
    postLists.addAll(items)

    ProfileScreen(currentUser = "cute_girl", postsList = items)
}