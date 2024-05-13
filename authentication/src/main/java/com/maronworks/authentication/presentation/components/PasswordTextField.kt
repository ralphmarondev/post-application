package com.maronworks.authentication.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Password",
    leadingIcon: ImageVector = Icons.Outlined.Lock,
) {
    var show by remember { mutableStateOf(true) }

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        label = {
            Text(
                text = label,
                fontFamily = FontFamily.Monospace
            )
        },
        textStyle = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500
        ),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = label
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(
                    onClick = {
                        show = !show
                    }
                ) {
                    Icon(
                        imageVector = if (show) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                        contentDescription = ""
                    )
                }
            }
        },
        visualTransformation = if (show) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true
    )
}