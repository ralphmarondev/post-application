package com.maronworks.postapplication.authentication.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.authentication.LoginSignUpViewModel
import com.maronworks.postapplication.ui.theme.PostApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Login(
    viewModel: LoginSignUpViewModel,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onLogin: () -> Unit,
) {
    var username by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var checked by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 15.dp),
            label = {
                Text(text = "Username")
            },
            textStyle = TextStyle(
                fontFamily = FontFamily.Monospace
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                if (username.isNotEmpty()) {
                    IconButton(onClick = { username = "" }) {
                        Icon(
                            imageVector = Icons.Outlined.Clear,
                            contentDescription = ""
                        )
                    }
                }
            }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 15.dp),
            label = {
                Text(text = "Password")
            },
            textStyle = TextStyle(
                fontFamily = FontFamily.Monospace
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Password,
                    contentDescription = ""
                )
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = !checked
                }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Remember Me")
        }

        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    if (viewModel.onLogin(username, password)) {
                        onLogin()
                        Log.d("hello", "Logged in successfully.")
                    } else {
                        Log.d("hello", "Invalid username and password")

                        scope.launch {
                            // we assume that all username [including space] is a valid username [blank username doesn't
                            // exists in our database though]
                            snackbarHostState.showSnackbar("Invalid password. Please try again.")
                        }
                    }
                } else {
                    Log.d("hello", "Username or password is empty.")

                    scope.launch {
                        snackbarHostState.showSnackbar("Username or password is empty.")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "LOGIN",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                fontFamily = FontFamily.Monospace,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(4f))
            Text(
                text = "OR",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
            Divider(modifier = Modifier.weight(4f))
        }

        ElevatedButton(
            onClick = {
                viewModel.selectedScreen.intValue = 1
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "REGISTER",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}

@Preview
@Composable
private fun LoginPreview() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val snackbarHostState = remember { SnackbarHostState() }

            Login(
                viewModel = LoginSignUpViewModel(LocalContext.current),
                scope = rememberCoroutineScope(),
                snackbarHostState = snackbarHostState,
                onLogin = {}
            )
        }
    }
}
