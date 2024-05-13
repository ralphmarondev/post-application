package com.maronworks.home.feed.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.outlined.AddComment
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.home.model.post.PostModel

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: PostModel,
    onMore: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = modifier
    ) {
        // ACCOUNT WHO POSTED IT
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = post.image),
                contentDescription = "",
                modifier = Modifier
                    .border(
                        border = BorderStroke(
                            2.dp,
                            brush = Brush.horizontalGradient(
                                listOf(Color.Magenta, Color.Red)
                            ),
                        ),
                        CircleShape
                    )
                    .size(45.dp)
                    .padding(4.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = post.name)
                Text(
                    text = post.date,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onMore) {
                Icon(
                    imageVector = Icons.Outlined.MoreHoriz,
                    contentDescription = ""
                )
            }
        }
        Divider(
            modifier = Modifier
                .padding(5.dp)
        )

        // POST CONTENTS
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
                .clickable { expanded = !expanded }
                .padding(15.dp)
        ) {
            Text(
                text = post.post,
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        Divider(modifier = Modifier.padding(5.dp))

        // REACTION BUTTONS
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .padding(bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            var liked by remember { mutableStateOf(false) }
            var comment by remember { mutableStateOf(false) }
            var share by remember { mutableStateOf(false) }
            var bookmark by remember { mutableStateOf(false) }

            val listOfIcons = listOf(
                IconModel(
                    value = liked,
                    defaultIcon = Icons.Outlined.FavoriteBorder,
                    selectedIcon = Icons.Filled.Favorite,
                    onClickedChanged = { liked = !liked }
                ),
                IconModel(
                    value = comment,
                    defaultIcon = Icons.Outlined.AddComment,
                    selectedIcon = Icons.Filled.AddComment,
                    onClickedChanged = { comment = !comment }
                ),
                IconModel(
                    value = share,
                    defaultIcon = Icons.Outlined.IosShare,
                    selectedIcon = Icons.Filled.IosShare,
                    onClickedChanged = { share = !share }
                ),
                IconModel(
                    value = bookmark,
                    defaultIcon = Icons.Outlined.BookmarkAdd,
                    selectedIcon = Icons.Filled.BookmarkAdd,
                    onClickedChanged = { bookmark = !bookmark }
                )
            )

            listOfIcons.forEachIndexed { _, item ->
                IconButtonComponent(
                    clicked = item.value,
                    defaultIcon = item.defaultIcon,
                    selectedIcon = item.selectedIcon,
                    onClickedChanged = item.onClickedChanged
                )
            }
        }
    }
}

private data class IconModel(
    val value: Boolean,
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector,
    val onClickedChanged: (Boolean) -> Unit
)

@Composable
private fun IconButtonComponent(
    modifier: Modifier = Modifier,
    clicked: Boolean,
    defaultIcon: ImageVector,
    selectedIcon: ImageVector,
    onClickedChanged: (Boolean) -> Unit
) {
    IconButton(
        onClick = { onClickedChanged(clicked) },
        modifier = modifier
    ) {
        Icon(
            imageVector = if (clicked) selectedIcon else defaultIcon,
            contentDescription = ""
        )
    }
}
