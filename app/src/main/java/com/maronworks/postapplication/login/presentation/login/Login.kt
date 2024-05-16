package com.maronworks.postapplication.login.presentation.login

import android.content.Context
import android.util.Log
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
import com.maronworks.postapplication.core.data.preferences.SharedPreferencesManager
import com.maronworks.postapplication.login.LoginViewModel
import com.maronworks.postapplication.login.model.user.UserModel
import com.maronworks.postapplication.login.presentation.components.NormalTextField
import com.maronworks.postapplication.login.presentation.components.PasswordTextField
import com.maronworks.postapplication.login.presentation.components.PrimaryButton
import com.maronworks.postapplication.login.presentation.components.RememberMeCheckBox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Login(
    modifier: Modifier = Modifier,
    context: Context,
    scope: CoroutineScope,
    viewModel: LoginViewModel,
    snackBarState: SnackbarHostState,
    pref: SharedPreferencesManager,
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

                        // save current user
                        Log.d("pref", "Setting [$username] as the current user.")
                        pref.setCurrentUser(username)
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