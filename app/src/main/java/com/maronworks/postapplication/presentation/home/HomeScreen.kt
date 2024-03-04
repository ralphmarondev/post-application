package com.maronworks.postapplication.presentation.home

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.maronworks.postapplication.components.PostCard
import com.maronworks.postapplication.data.DBHandler
import com.maronworks.postapplication.model.PostModel
import com.maronworks.postapplication.model.postModelItems

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    itemsList: MutableList<PostModel>
) {
    val context = LocalContext.current
    val db = DBHandler(context)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Post Application", fontFamily = FontFamily.Monospace)
                },
                actions = {
                    IconButton(
                        onClick = {
                            try {
                                db.deleteAllCurrentUser()
                                Log.d("clearing current user", "Success!")
                            } catch (e: Exception) {
                                Log.d("clearing current user", "Error: ${e.message}")
                            }
                            navController.popBackStack()
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
                items(itemsList.size) { index ->
                    PostCard(
                        item = itemsList[index]
                    )
                }
                // TODO: Add option to delete this default posts.
                items(postModelItems.size) { index ->
                    PostCard(
                        item = postModelItems[index]
                    )
                }
                // padding on the bottom
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPrev() {
//    HomeScreen(navController = rememberNavController())
}