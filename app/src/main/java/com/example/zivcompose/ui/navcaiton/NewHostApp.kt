package com.example.zivcompose.ui.navcaiton

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zivcompose.ui.pages.main.FirstView
import com.example.zivcompose.ui.pages.main.MainView

@Composable
fun NewHostApp(){
    val nav: NewNavHost = viewModel()

    when(nav.currentDestination){
        RouterConst.firstView-> FirstView()
        RouterConst.mainView-> MainView()
        else-> MainView()

    }
}