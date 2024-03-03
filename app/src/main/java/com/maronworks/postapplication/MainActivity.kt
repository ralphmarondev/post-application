package com.maronworks.postapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.maronworks.postapplication.presentation.MyApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}
//git remote add origin https://github.com/ralphmarondev/database-template.git
//git branch -M main
//git push -u origin main