package com.example.zivcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.zivcompose.http.service.TestService
import com.example.zivcompose.ui.theme.ZivComposeTheme
import com.example.zivcompose.util.ShowToast
import com.tencent.mmkv.MMKV


class MainActivity : ComponentActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化
        MMKV.initialize(this)//本地存储
        ShowToast.getInstance().init(this)//showToast，提示弹窗

        //网络请求测试，看控制台输出
        val testService = TestService()
        testService.musicEnY
        testService.musicY
        testService.music
        testService.musicEn

        //状态烂文字变黑
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        setContent {
            ZivComposeTheme {
                Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ZivComposeTheme {
        Greeting("Android")
    }
}