package com.maronworks.postapplication.login.presentation.register

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maronworks.postapplication.login.LoginViewModel
import com.maronworks.postapplication.login.model.user.UserModel
import com.maronworks.postapplication.login.presentation.components.NormalTextField
import com.maronworks.postapplication.login.presentation.components.PasswordTextField
import com.maronworks.postapplication.login.presentation.components.PrimaryButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Register(
    modifier: Modifier = Modifier, context: Context,
    scope: CoroutineScope,
    viewModel: LoginViewModel,
    snackBarState: SnackbarHostState,
    onRegister: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var snackBarMsg by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        NormalTextField(
            value = username,
            label = "Username",
            leadingIcon = Icons.Outlined.AccountCircle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp),
            onValueChange = {
                username = it
            }
        )
        PasswordTextField(
            value = password,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp),
            onValueChange = {
                password = it
            }
        )
        PasswordTextField(
            value = confirmPassword,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp),
            onValueChange = {
                confirmPassword = it
            },
            label = "Confirm Password"
        )

        PrimaryButton(
            text = "REGISTER",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 20.dp, bottom = 20.dp),
            onClick = {
                if (username.isEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                    snackBarMsg = "Username cannot be empty."
                } else if (username.isNotEmpty() && password.isEmpty() || (password != confirmPassword)) {
                    snackBarMsg = "Password did not match."
                } else if (username.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {
                    snackBarMsg = "Fields are empty!"
                } else if (username.isNotEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {
                    snackBarMsg = "Password field is empty!"
                } else {
                    if (password == confirmPassword) {
                        if (viewModel.createUser(context, user = UserModel(username, password))) {
                            onRegister()
                            snackBarMsg = "User '$username' registered successfully."
                        } else {
                            snackBarMsg = "User '$username' saving failed."
                        }
                    } else {
                        snackBarMsg = "Password did not match."
                    }
                }

                scope.launch {
                    snackBarState.showSnackbar(snackBarMsg)
                }
            }
        )
    }
}