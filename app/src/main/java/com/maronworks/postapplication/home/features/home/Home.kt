package com.maronworks.postapplication.home.features.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maronworks.postapplication.home.HomeViewModel
import com.maronworks.postapplication.home.domain.post.PostModel
import com.maronworks.postapplication.home.features.home.components.BottomSheetContent
import com.maronworks.postapplication.home.features.home.components.PostContainer
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(
    viewModel: HomeViewModel,
    posts: MutableList<PostModel>,
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Post App")
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onDarkTheme()
                    }) {
                        Icon(
                            imageVector = if (viewModel.isDarkTheme.value) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
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
                Spacer(modifier = Modifier.height(10.dp))
            }

            if (posts.size == 0) {
                item {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "No posts yet. Go and make one!",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 10.dp)
                        )
                    }
                }
            } else {
                items(posts.size) { index ->
                    PostContainer(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        post = PostModel(
                            ownerPicture = posts[index].ownerPicture,
                            ownerName = posts[index].ownerName,
                            postContent = posts[index].postContent,
                            datePosted = posts[index].datePosted
                        ),
                        onMoreClick = {
                            showBottomSheet = !showBottomSheet
                        }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                }
            ) {
                BottomSheetContent(
                    onDismiss = {
                        showBottomSheet = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomePreview() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            Home(viewModel = HomeViewModel(LocalContext.current))
        }
    }
}
