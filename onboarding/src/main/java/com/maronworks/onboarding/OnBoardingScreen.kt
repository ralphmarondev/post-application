package com.maronworks.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnBoardingScreen(
    onGetStarted: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onGetStarted) {
                Text(
                    text = "GET STARTED",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.compose),
                    contentDescription = "",
                    modifier = Modifier
                        .size(130.dp)
                )

                Spacer(modifier = Modifier.padding(top = 15.dp))
                Text(
                    text = "Post Application",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Register an account, create post, make friends, interact with other users.",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
