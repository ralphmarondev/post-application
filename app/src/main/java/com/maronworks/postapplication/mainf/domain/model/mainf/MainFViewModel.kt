package com.maronworks.postapplication.mainf.domain.model.mainf

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class MainFViewModel : ViewModel(){
    var selectedIndex = mutableIntStateOf(0)

    fun onChangeIndex(index: Int) {
        selectedIndex.intValue = index
    }

    fun goBackHome(){
        selectedIndex.intValue = 0
    }
}