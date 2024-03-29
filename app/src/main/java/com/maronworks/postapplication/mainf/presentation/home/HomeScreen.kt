package com.maronworks.postapplication.mainf.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.maronworks.postapplication.mainf.data.dummy.callme.CallMePostCard
import com.maronworks.postapplication.mainf.data.dummy.notification.NotificationPostCard
import com.maronworks.postapplication.mainf.data.dummy.playaudio.PlayAudioPostCard
import com.maronworks.postapplication.mainf.data.dummy.sourcecode.SourceCodePostCard
import com.maronworks.postapplication.mainf.data.dummy.typewritter.TypeWriterPostCard
import com.maronworks.postapplication.mainf.data.dummy.typewritter.TypeWriterViewModel
import com.maronworks.postapplication.mainf.data.dummy.writefile.WriteFilePostCard
import com.maronworks.postapplication.mainf.domain.model.home.HomeViewModel
import com.maronworks.postapplication.mainf.domain.model.newpost.PostModel
import com.maronworks.postapplication.mainf.presentation.home.components.PostCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    post: MutableList<PostModel>
) {
    val vm = HomeViewModel()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Post Application", fontFamily = FontFamily.Monospace)
                },
                actions = {
                    IconButton(
                        onClick = {
                            vm.onExit(
                                context = context,
                                navController = navController
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ExitToApp,
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = Color.Red
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 5.dp, end = 5.dp)
            ) {
                items(post.size) { index ->
                    PostCard(post = post[index])
                }
                item {
                    TypeWriterPostCard()
                }
                item{
                    WriteFilePostCard()
                }
                item {
                    CallMePostCard()
                }
                item {
                    PlayAudioPostCard()
                }
                item {
                    NotificationPostCard()
                }
                item {
                    SourceCodePostCard()
                }
                // padding on the bottom
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}