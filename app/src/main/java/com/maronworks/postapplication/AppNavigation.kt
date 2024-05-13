package com.maronworks.postapplication

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.maronworks.authentication.AuthenticationActivity
import com.maronworks.core.preferences.SharedPreferencesManager
import com.maronworks.home.HomeActivity
import com.maronworks.onboarding.OnBoardingActivity

@Composable
fun AppNavigation(
    context: Context = LocalContext.current,
    onFinish: () -> Unit
) {
    val preference = SharedPreferencesManager(context)

    // showing onboarding or not
    if (preference.isFirstLaunch()) {
        val intent = Intent(context, OnBoardingActivity::class.java)
        context.startActivity(intent)

        // set onboarding completed
        preference.setOnBoardingCompleted()
    }

    // showing authentication or not
    if (preference.isRememberMe()) {
        // go to home screen
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)

        // exit app
        onFinish()
    } else {
        // go to authentication screen
        val intent = Intent(context, AuthenticationActivity::class.java)
        context.startActivity(intent)

        // if authentication successful [go back here and go to home screen]
        val homeIntent = Intent(context, HomeActivity::class.java)
        context.startActivity(homeIntent)

        // exit app
        onFinish()
    }
}