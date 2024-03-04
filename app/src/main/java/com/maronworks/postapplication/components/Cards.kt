package com.maronworks.postapplication.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.PopupProperties
import androidx.core.graphics.drawable.toDrawable
import com.maronworks.postapplication.R
import com.maronworks.postapplication.data.DBHandler
import com.maronworks.postapplication.model.PostModel
import com.maronworks.postapplication.model.postModelItems

@Composable
fun PostCard(
    item: PostModel
) {
    val context = LocalContext.current
    val db = DBHandler(context)
    var currentUser by rememberSaveable {
        mutableStateOf("")
    }
    try {
        currentUser = db.readCurrentUser()
        Log.d("reading current uses", "Success! Value: $currentUser")
    } catch (e: Exception) {
        Log.d("reading current user", "Error: ${e.message}")
    }

    var clickedEmail by rememberSaveable {
        mutableStateOf(false)
    }
    var clickedFavorite by rememberSaveable {
        mutableStateOf(false)
    }
    var clickedRepost by rememberSaveable {
        mutableStateOf(false)
    }
    var clickedShare by rememberSaveable {
        mutableStateOf(false)
    }
    // TODO: Change the icons to their appropriate icons
    val icons = listOf(
        ReactionModel(
            selectedIcon = Icons.Filled.Email,
            defaultIcon = Icons.Outlined.MailOutline,
            onClick = { clickedEmail = !clickedEmail }
        ),
        ReactionModel(
            selectedIcon = Icons.Filled.Favorite,
            defaultIcon = Icons.Outlined.FavoriteBorder,
            onClick = { clickedFavorite = !clickedFavorite }
        ),
        ReactionModel(
            selectedIcon = Icons.Filled.AccountBox,
            defaultIcon = Icons.Outlined.AccountBox,
            onClick = { clickedRepost = !clickedRepost }
        ),
        ReactionModel(
            selectedIcon = Icons.Filled.Share,
            defaultIcon = Icons.Outlined.Share,
            onClick = { clickedShare = !clickedShare }
        ),
    )
    var expanded by rememberSaveable { mutableStateOf(false) }
    var showDropMenu by rememberSaveable {
        mutableStateOf(false)
    }

    Box {
        Card(
            modifier = Modifier
                .padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            ) {
                // profile and name row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Color.Green),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.userCreated,//userCreated,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            showDropMenu = !showDropMenu
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Divider()

                Text(
                    text = item.label,//label,
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                        .padding(5.dp),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    maxLines = if (expanded) Int.MAX_VALUE else 4,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = item.datePosted,//datePosted,
                    modifier = Modifier
                        .padding(5.dp),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.tertiary
                )

                Divider()
                // reaction row
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ReactionIconButton(
                        icon = if (clickedEmail) icons[0].selectedIcon else icons[0].defaultIcon,
                        onClick = { clickedEmail = !clickedEmail }
                    )
                    ReactionIconButton(
                        icon = if (clickedFavorite) icons[1].selectedIcon else icons[1].defaultIcon,
                        onClick = { clickedFavorite = !clickedFavorite }
                    )
                    ReactionIconButton(
                        icon = if (clickedRepost) icons[2].selectedIcon else icons[2].defaultIcon,
                        onClick = { clickedRepost = !clickedRepost }
                    )
                    ReactionIconButton(
                        icon = if (clickedShare) icons[3].selectedIcon else icons[3].defaultIcon,
                        onClick = { clickedShare = !clickedShare }
                    )
                }

                // dialogs
                if (showDropMenu) {
                    if (currentUser == item.userCreated) {
                        ShowDropMenuDialogCurrentUser(
                            post = item,
                            onDismiss = {
                                showDropMenu = false
                            }
                        )
                    } else {
                        ShowDropMenuDialogOthers(
                            onDismiss = {
                                showDropMenu = false
                            }
                        )
                    }
                }
            }
        }
    }
}

private data class ReactionModel(
    val selectedIcon: ImageVector,
    val defaultIcon: ImageVector,
    val onClick: () -> Unit
)

@Composable
private fun ReactionIconButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CardPrev() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
//        postModelItems.forEachIndexed { _, item ->
//            PostCard(
//                userCreated = item.userCreated,
//                label = item.label,
//                datePosted = item.datePosted
//            )
//        }
    }
}