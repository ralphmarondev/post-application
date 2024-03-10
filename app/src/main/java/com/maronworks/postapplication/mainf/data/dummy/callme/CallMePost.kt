package com.maronworks.postapplication.mainf.data.dummy.callme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.R


@Composable
fun CallMePostCard() {
    val callMeVM = CallMePostViewModel()
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
                        text = "My Application",//userCreated,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(45.dp)) // only show this if icon button is not shown
                }
                Divider()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 200.dp)
                        .padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Wondering on how you can tell me how cute I am? Call me.",
                        fontFamily = FontFamily.Monospace
                    )

                    ElevatedButton(
                        onClick = { callMeVM.launchPhoneDialer(context) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {
                        Text(
                            text = "Call Me. Press here!",
                            fontFamily = FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                Text(
                    text = "2024-03-11 at 2:19AM",// date and time I am writing this lol,
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
                        icon = callMeVM.commentIcon(),
                        onClick = { callMeVM.onCommentClick() }
                    )

                    ReactionIconButton(
                        icon = callMeVM.favoriteIcon(),
                        onClick = { callMeVM.onFavoriteClick() }
                    )

                    ReactionIconButton(
                        icon = callMeVM.repostIcon(),
                        onClick = { callMeVM.onRepostClick() }
                    )

                    ReactionIconButton(
                        icon = callMeVM.shareIcon(),
                        onClick = { callMeVM.onShareClick() }
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