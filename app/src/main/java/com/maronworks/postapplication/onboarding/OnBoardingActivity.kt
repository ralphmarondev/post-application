package com.maronworks.postapplication.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maronworks.postapplication.ui.theme.PostApplicationTheme

@Composable
fun OnBoardingActivity() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "POST APPLICATION",
            fontWeight = FontWeight.W600,
            fontSize = 20.sp
        )

        ElevatedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text(
                text = "GET STARTED",
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 10.dp)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun OnBoardingPreview() {
    PostApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            OnBoardingActivity()
        }
    }
}
