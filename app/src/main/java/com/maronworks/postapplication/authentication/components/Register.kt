package com.maronworks.postapplication.authentication.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

@Composable
fun Register(
    viewModel: LoginSignUpViewModel,
    onRegister: () -> Unit,
) {
    var username by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var password2 by rememberSaveable {
        mutableStateOf("")
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

        OutlinedTextField(
            value = password2,
            onValueChange = { password2 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 15.dp),
            label = {
                Text(text = "Confirm Password")
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

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty() && (password == password2)) {
                    if (viewModel.registerUser(username, password)) {
                        onRegister()
                    } else {
                        Log.d("db", "Failed to register user.")
                    }
                } else {
                    Log.d("db", "Username or password is empty")
                }
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
                viewModel.selectedScreen.intValue = 0
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
    }
}

@Preview
@Composable
private fun RegisterPreview() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Register(viewModel = LoginSignUpViewModel(LocalContext.current)) {

            }
        }
    }
}
