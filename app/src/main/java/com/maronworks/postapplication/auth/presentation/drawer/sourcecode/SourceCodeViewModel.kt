package com.maronworks.postapplication.auth.presentation.drawer.sourcecode

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel

class SourceCodeViewModel : ViewModel(){
    fun visitSourceCode(context: Context) {
        val url = "https://github.com/ralphmarondev/post-application/"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent) // opens the default browser
    }
}