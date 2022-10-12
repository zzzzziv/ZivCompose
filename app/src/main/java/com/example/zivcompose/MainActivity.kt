package com.example.zivcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.zivcompose.ui.navcaiton.NavHostApp
import com.example.zivcompose.ui.theme.ZivComposeTheme
import com.example.zivcompose.util.ShowToast
import com.tencent.mmkv.MMKV


class MainActivity : ComponentActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取状态栏高度
//        var statusBarHeight = 0
//        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
//        if (resourceId > 0) {
//            statusBarHeight = resources.getDimensionPixelSize(resourceId)
//        }

        //处理不同机型，状态栏不透明问题
//        window.statusBarColor = Color.Transparent.value.toInt()
//        //处理不同机型，导航栏遮盖内容问题
//        window.decorView.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        //让内容，显示在状态栏和系统导航栏后面：状态栏和导航栏会遮盖部分内容
        WindowCompat.setDecorFitsSystemWindows(window, false)
//        //状态烂文字变黑
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        //初始化
        MMKV.initialize(this)//本地存储
        ShowToast.getInstance().init(this)//showToast，提示弹窗



        setContent {
            ZivComposeTheme {
                Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
                    NavHostApp()
                }
            }
        }
    }
}


