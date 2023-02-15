package com.example.zivcompose.ui.navcaiton

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation

class NewNavHost:ViewModel(){
    val startDestination = RouterConst.mainView

    var currentDestination by mutableStateOf(RouterConst.mainView)
    private set

   fun navigate(routh:String){
        this.currentDestination=routh
    }





}