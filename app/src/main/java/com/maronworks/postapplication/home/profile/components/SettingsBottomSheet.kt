package com.maronworks.postapplication.home.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onLogout: () -> Unit
) {
    val listOfBottomSheetModel = listOf(
        SettingsBottomSheetModel(
            icon = Icons.Outlined.AccountBox,
            text = "Add Account",
            onClick = {}
        ),
        SettingsBottomSheetModel(
            icon = Icons.AutoMirrored.Outlined.Logout,
            text = "Logout",
            onClick = onLogout
        )
    )
    ModalBottomSheet(onDismissRequest = onDismiss, modifier = modifier) {
        Column(
            modifier = Modifier
                .defaultMinSize(minHeight = 500.dp)
                .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 150.dp)
        ) {
            Text(
                text = "CREATE",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W600,
                fontSize = 18.sp
            )
            Divider(modifier = Modifier.padding(vertical = 10.dp))

            repeat(listOfBottomSheetModel.size) {
                ElevatedCard(
                    onClick = listOfBottomSheetModel[it].onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Icon(
                            imageVector = listOfBottomSheetModel[it].icon,
                            contentDescription = listOfBottomSheetModel[it].text
                        )

                        Text(
                            text = listOfBottomSheetModel[it].text,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500
                        )
                    }
                }
            }
        }
    }
}

private data class SettingsBottomSheetModel(
    val icon: ImageVector,
    val text: String,
    val onClick: () -> Unit
)
