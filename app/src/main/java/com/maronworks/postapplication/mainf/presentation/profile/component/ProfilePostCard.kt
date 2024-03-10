package com.maronworks.postapplication.mainf.presentation.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.maronworks.postapplication.R
import com.maronworks.postapplication.mainf.domain.model.home.HomeViewModel
import com.maronworks.postapplication.mainf.domain.model.newpost.PostModel
import com.maronworks.postapplication.mainf.domain.model.profile.ProfileViewModel

@Composable
fun ProfilePostCard(
    profileVM: ProfileViewModel,
    post: PostModel
) {
    val vm = HomeViewModel()
    val context = LocalContext.current

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
                        text = post.userCreated,//userCreated,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { profileVM.showDialog() }) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
                Divider()

                Text(
                    text = post.postContent,//label,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { vm.onExpand() }
                        .padding(5.dp),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    maxLines = vm.postContentMaxLines(),
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = post.datePosted,//datePosted,
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
                        icon = vm.commentIcon(),
                        onClick = { vm.onCommentClick() }
                    )

                    ReactionIconButton(
                        icon = vm.favoriteIcon(),
                        onClick = { vm.onFavoriteClick() }
                    )

                    ReactionIconButton(
                        icon = vm.repostIcon(),
                        onClick = { vm.onRepostClick() }
                    )

                    ReactionIconButton(
                        icon = vm.shareIcon(),
                        onClick = { vm.onShareClick() }
                    )
                }
            }
        }

        if (profileVM.getDialogState()) {
            Dialog(onDismissRequest = { profileVM.closeDialog() }) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ElevatedButton(
                            onClick = { },
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            Text(
                                text = "EDIT",
                                fontFamily = FontFamily.Monospace
                            )
                        }

                        ElevatedButton(
                            onClick = {
                                profileVM.deletePost(context = context, post = post)
                                profileVM.closeDialog()
                            },
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            Text(
                                text = "DELETE",
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PostDialog(
    onClick: () -> Unit,
    onDismiss: () -> Unit
) {

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ElevatedButton(
                    onClick = { },
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Text(
                        text = "EDIT",
                        fontFamily = FontFamily.Monospace
                    )
                }

                ElevatedButton(
                    onClick = {
                        onClick()
                    },
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Text(
                        text = "DELETE",
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}

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