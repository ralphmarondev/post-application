package com.maronworks.postapplication.auth.presentation.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.auth.domain.model.login.LoginViewModel


@Composable
fun Register(
    onRegister: () -> Unit
) {
    val vm = LoginViewModel()
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = vm.fullName.value,
                onValueChange = {
                    vm.fullName.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AccountBox,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                placeholder = {
                    Text(
                        text = "Full Name...",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                singleLine = true
            )

            OutlinedTextField(
                value = vm.username.value,
                onValueChange = {
                    vm.username.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                placeholder = {
                    Text(
                        text = "Username...",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                singleLine = true
            )

            OutlinedTextField(
                value = vm.password.value,
                onValueChange = {
                    vm.password.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                placeholder = {
                    Text(
                        text = "Password...",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    vm.onRegister(context)
                    onRegister()
                }
            ) {
                Text(
                    text = "REGISTER",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
            }
        }
    }
}