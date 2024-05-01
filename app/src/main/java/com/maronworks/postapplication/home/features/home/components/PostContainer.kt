package com.maronworks.postapplication.home.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.CommentBank
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.R
import com.maronworks.postapplication.home.domain.post.PostModel
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

@Composable
fun PostContainer(
    modifier: Modifier = Modifier,
    post: PostModel,
    onMoreClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = post.ownerPicture),
                    contentDescription = "",
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = post.ownerName)

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )
                IconButton(onClick = onMoreClick) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = ""
                    )
                }
            }

            Divider(modifier = Modifier.padding(top = 10.dp))

            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
            ) {
                Text(text = post.postContent)
            }

            Text(
                text = post.datePosted,
                fontSize = 13.sp,
                fontStyle = FontStyle.Normal
            )

            Divider()
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val listOfNavIcons = listOf(
                    Icons.Outlined.FavoriteBorder,
                    Icons.Outlined.CommentBank,
                    Icons.Outlined.IosShare,
                    Icons.Outlined.BookmarkAdd
                )

                listOfNavIcons.forEachIndexed { _, item ->
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = item,
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PostContainerPrev() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    PostContainer(
                        modifier = Modifier
                            .padding(10.dp),
                        post = PostModel(
                            ownerName = "Ralph Maron Eda",
                            ownerPicture = R.drawable.sample_image,
                            datePosted = "2024-04-30 @ 11:30PM",
                            postContent = "Hello world. This is some content."
                        ),
                        onMoreClick = {}
                    )
                }
            }
        }
    }
}
