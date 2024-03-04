package com.maronworks.postapplication.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.maronworks.postapplication.data.DBHandler
import com.maronworks.postapplication.model.PostModel
import com.maronworks.postapplication.model.postModelItems

@Composable
fun ShowDropMenuDialogOthers(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 25.dp, bottom = 25.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        Toast.makeText(context, "Coming soon...", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@Composable
fun ShowDropMenuDialogCurrentUser(
    post: PostModel,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var showDelete by rememberSaveable {
        mutableStateOf(false)
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 15.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        Toast.makeText(context, "Coming soon...", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Save")
                }

                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Edit")
                }

                OutlinedButton(
                    onClick = {
                        showDelete = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Delete")
                }
            }
        }
        if(showDelete){
            DeleteDialog(post = post) {
                showDelete = false
                onDismiss()
            }
        }
    }
}

@Composable
private fun DeleteDialog(post: PostModel, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val db = DBHandler(context)

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Are you sure you want to delete?",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                Text(
                    text = post.label,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = post.datePosted,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                ElevatedButton(
                    onClick = {
                        try {
                            db.deletePost(post.id, post.userCreated)
                            Log.d("delete post", "Deleted successfully")
                            onDismiss()
                        } catch (e: Exception) {
                            Log.d("delete post", "Error: ${e.message}")
                        }
                    }
                ) {
                    Text(
                        text = "DELETE",
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ShowDropDownDialog() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        DeleteDialog(
            post = PostModel(
                id = 0,
                userCreated = "ralphmaron",
                label = "I have done so much for others. It's time to do things for myself.",
                datePosted = "2024-03-04 at 10:06PM"
            )
        ) {

        }
    }
}