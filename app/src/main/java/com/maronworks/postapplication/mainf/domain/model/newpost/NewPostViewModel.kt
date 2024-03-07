package com.maronworks.postapplication.mainf.domain.model.newpost

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NewPostViewModel : ViewModel(){
    var newPost = mutableStateOf("")

}