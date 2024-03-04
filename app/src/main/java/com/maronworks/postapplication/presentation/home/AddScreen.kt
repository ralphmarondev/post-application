package com.maronworks.postapplication.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.data.DBHandler
import com.maronworks.postapplication.utils.getCurrentDateTime
import com.maronworks.postapplication.utils.readFromFile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    onPostClick: () -> Unit
) {
    var newPost by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val db = DBHandler(context)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "New Post", fontFamily = FontFamily.Monospace)
                },
                navigationIcon = {
                    IconButton(onClick = onPostClick) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            try {
                                db.savePost(
                                    userCreated = db.readCurrentUser(),// "ralphmaron",//readFromFile().toString(),
                                    label = newPost,
                                    datePosted = getCurrentDateTime()
                                )
                                Log.d("post", "Content: $newPost")
                                onPostClick()
                            } catch (e: Exception) {
                                Log.d("insert post", "Error: ${e.message}")
                            }
                        }
                    ) {
                        Text(
                            text = "POST",
                            fontFamily = FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 5.dp)
            ) {
                OutlinedTextField(
                    value = newPost,
                    onValueChange = { newPost = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.secondary
                    ),
                    placeholder = {
                        Text(
                            text = "Post Something...",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.W500,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    },
                    minLines = 5
                )

            }
        }
    }
}

@Preview
@Composable
private fun AddScreenPrev() {
    AddScreen {}
}
