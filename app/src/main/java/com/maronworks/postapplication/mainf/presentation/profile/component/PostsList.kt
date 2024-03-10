package com.maronworks.postapplication.mainf.presentation.profile.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maronworks.postapplication.mainf.domain.model.newpost.PostModel
import com.maronworks.postapplication.mainf.domain.model.profile.ProfileViewModel

@Composable
fun PostList(
    vm: ProfileViewModel,
    post: MutableList<PostModel>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp)
        ) {
            post.forEachIndexed { _, item ->
                ProfilePostCard(profileVM = vm, post = item)
            }
            // padding at the bottom
            Spacer(modifier = Modifier.padding(100.dp))
        }
    }
}