package com.maronworks.authentication.presentation.login

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
import com.maronworks.authentication.presentation.LoginViewModel
import com.maronworks.authentication.presentation.components.NormalTextField
import com.maronworks.authentication.presentation.components.PasswordTextField
import com.maronworks.authentication.presentation.components.PrimaryButton
import com.maronworks.authentication.presentation.components.RememberMeCheckBox
import com.maronworks.core.db.auth.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Login(
    modifier: Modifier = Modifier,
    context: Context,
    scope: CoroutineScope,
    viewModel: LoginViewModel,
    snackBarState: SnackbarHostState,
    onLogin: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var rememberMe by remember { mutableStateOf(false) }
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

        RememberMeCheckBox(
            checked = rememberMe,
            onCheckedChange = { rememberMe = !rememberMe },
            modifier = Modifier
                .fillMaxWidth()
        )

        PrimaryButton(
            text = "LOGIN",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 20.dp, bottom = 20.dp),
            onClick = {
                if (username.isEmpty() && password.isEmpty()) {
                    snackBarMsg = "Username and password is empty!"
                    scope.launch {
                        snackBarState.showSnackbar(snackBarMsg)
                    }
                } else if (username.isEmpty() && password.isNotEmpty()) {
                    snackBarMsg = "Username cannot be empty."
                    scope.launch {
                        snackBarState.showSnackbar(snackBarMsg)
                    }
                } else if (username.isNotEmpty() && password.isEmpty()) {
                    snackBarMsg = "Password is empty"
                    scope.launch {
                        snackBarState.showSnackbar(snackBarMsg)
                    }
                } else {
                    if (viewModel.isUserExists(context, user = UserModel(username, password))) {
                        onLogin()
//                        snackBarMsg = "Login successful!"
                    } else {
                        snackBarMsg = "Incorrect Password. Please try again."
                        scope.launch {
                            snackBarState.showSnackbar(snackBarMsg)
                        }
                    }
                }
            }
        )
    }
}