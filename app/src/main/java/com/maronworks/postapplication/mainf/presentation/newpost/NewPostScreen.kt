package com.maronworks.postapplication.mainf.presentation.newpost

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.mainf.domain.model.newpost.NewPostViewModel

private val vm = NewPostViewModel()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPostScreen(
    onPost: () -> Unit,
    onClose: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "New Post", fontFamily = FontFamily.Monospace)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onClose()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            onPost()
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
                    value = vm.newPost.value,
                    onValueChange = { vm.newPost.value = it },
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