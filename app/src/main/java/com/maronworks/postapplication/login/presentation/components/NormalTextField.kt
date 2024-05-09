package com.maronworks.postapplication.login.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NormalTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Some text",
    leadingIcon: ImageVector,
) {
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
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = ""
                    )
                }
            }
        },
        singleLine = true
    )
}