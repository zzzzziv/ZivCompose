package com.example.zivcompose.ui.pages.main

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.blankj.utilcode.util.BarUtils
import com.example.zivcompose.ui.navcaiton.NewHostApp
import com.example.zivcompose.ui.theme.ZivComposeTheme
import com.example.zivcompose.util.ShowToast
import com.google.accompanist.insets.ProvideWindowInsets
import com.tencent.mmkv.MMKV


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BarUtils.transparentStatusBar(this)//状态栏透明

        setContent {
            if (isSystemInDarkTheme()){
                BarUtils.setStatusBarLightMode(this@MainActivity,false)
            }else {
                BarUtils.setStatusBarLightMode(this@MainActivity,true)
            }
                ZivComposeTheme {
                        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
                            val config: Configuration = resources.configuration
                            val screenWidth = config.screenWidthDp
                            val screenHeight = config.screenHeightDp
                            Log.d("===","最大高度：$screenHeight")
                            Log.d("===","最大宽度：$screenWidth")
                            NewHostApp()
                        }

            }

        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}


