package com.maronworks.postapplication.authentication.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuOpen
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

@Preview
@Composable
private fun Default() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Login(onLogin = { /*TODO*/ }) {

            }
        }
    }
}

private val loginViewModel = LoginViewModel()

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Login(
    onLogin: () -> Unit,
    onRegister: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.MenuOpen,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedCard(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(15.dp)
                ) {
                    OutlinedTextField(
                        value = loginViewModel.username.value,
                        onValueChange = {
                            loginViewModel.username.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        label = {
                            Text(
                                text = "Username",
                                fontFamily = FontFamily.Monospace
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        trailingIcon = {
                            if (loginViewModel.username.value.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        loginViewModel.username.value = ""
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Clear,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.W500
                        )
                    )
                    OutlinedTextField(
                        value = loginViewModel.password.value,
                        onValueChange = {
                            loginViewModel.password.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        label = {
                            Text(
                                text = "Password",
                                fontFamily = FontFamily.Monospace
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Password,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        trailingIcon = {
                            if (loginViewModel.password.value.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        loginViewModel.toggleShowPassword()
                                    }
                                ) {
                                    Icon(
                                        imageVector =
                                        if (loginViewModel.showPassword.value) Icons.Outlined.Visibility
                                        else Icons.Outlined.VisibilityOff,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.W500
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        visualTransformation = if(loginViewModel.showPassword.value) VisualTransformation.None else PasswordVisualTransformation()
                    )

                    TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .align(Alignment.Start)
                    ) {
                        Text(
                            text = "Forgot Password?",
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = onLogin,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Text(
                            text = "LOGIN",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Spacer(
                            modifier = Modifier
                                .weight(6f)
                                .height(2.dp)
                                .background(Color.LightGray)
                        )
                        Text(
                            text = "OR",
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .weight(6f)
                                .height(2.dp)
                                .background(Color.LightGray)
                        )
                    }

                    ElevatedButton(
                        onClick = onRegister,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Text(
                            text = "REGISTER",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                        )
                    }
                }
            }
        }
    }
}