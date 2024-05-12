package com.maronworks.postapplication.home.feed.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.R
import com.maronworks.postapplication.home.model.post.PostModel
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .padding(bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val listOfIcons = listOf(
                IconModel(
                    defaultIcon = Icons.Outlined.FavoriteBorder,
                    selectedIcon = Icons.Filled.Favorite,
                    onClick = {}
                ),
                IconModel(
                    defaultIcon = Icons.Outlined.AddComment,
                    selectedIcon = Icons.Filled.AddComment,
                    onClick = {}
                ),
                IconModel(
                    defaultIcon = Icons.Outlined.IosShare,
                    selectedIcon = Icons.Filled.IosShare,
                    onClick = {}
                ),
                IconModel(
                    defaultIcon = Icons.Outlined.BookmarkAdd,
                    selectedIcon = Icons.Filled.BookmarkAdd,
                    onClick = {}
                )
            )

            listOfIcons.forEachIndexed { _, iconModel ->
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = iconModel.defaultIcon,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

private data class IconModel(
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector,
    val onClick: () -> Unit
)

@Composable
private fun IconButtonComponent(
    modifier: Modifier = Modifier,
    active: Boolean,
    onClick: (Boolean) -> Unit,
    imageVector: IconModel
) {
    IconButton(
        onClick = {
            active != active
            onClick(active)
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = if (active) imageVector.selectedIcon else imageVector.defaultIcon,
            contentDescription = ""
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PostCardPreview() {
    val post = PostModel(
        name = "Ralph Maron Eda",
        image = R.drawable.cute_me,
        post = "Hello there, Ralph Maron Eda is here. He is a compute Enigneering student is passionate in learning new technologies. He likes mobile development, so as web development. He is also interested in machine learning and artificial intelligence.",
        date = "2024-05-11 | 7:58"
    )
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                PostCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                    post = post,
                    onMore = {}
                )
            }
        }
    }
}