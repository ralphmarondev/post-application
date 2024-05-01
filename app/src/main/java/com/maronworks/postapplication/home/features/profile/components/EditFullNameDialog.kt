package com.maronworks.postapplication.home.features.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.maronworks.postapplication.MainViewModel
import com.maronworks.postapplication.core.util.debugLog
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

@Composable
fun EditFullNameDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
) {
    var newFullName by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
//                .defaultMinSize(minHeight = 300.dp)
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp)
            ) {

                OutlinedTextField(
                    value = newFullName,
                    onValueChange = {
                        newFullName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    label = {
                        Text(text = "New Name")
                    },
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    ElevatedButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "CANCEL",
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Button(
                        onClick = {
                            viewModel.currentFullName.value = newFullName
                            debugLog("New Full Name: $newFullName")

                            // close
                            onDismiss()
                        },
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "SAVE",
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Default() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                var showDialog by remember { mutableStateOf(true) }

                if (showDialog) {
                    EditFullNameDialog(
                        viewModel = MainViewModel(),
                        onDismiss = { showDialog = false },
                        onSave = {}
                    )
                }
            }
        }
    }
}
