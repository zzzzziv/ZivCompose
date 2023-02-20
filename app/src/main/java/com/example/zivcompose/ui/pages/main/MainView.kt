package com.example.zivcompose.ui.pages.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zivcompose.ui.components.Loading
import com.example.zivcompose.ui.components.SimpleDropDownMenu
import com.example.zivcompose.ui.components.SwiperContent
import com.example.zivcompose.ui.components.TapBar
import com.example.zivcompose.ui.navcaiton.NewNavHost
import com.example.zivcompose.ui.viewmodel.FirstViewModel
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun MainView() {

    val nav: NewNavHost = viewModel()
    var bColor by remember {
        mutableStateOf(Color.Gray)
    }
    var currChoose by remember {
        mutableStateOf("")
    }

    val firstvm: FirstViewModel = viewModel()
    //类型数据
    val swipeData = listOf<String>(
        "https://img1.baidu.com/it/u=645828909,2832941895&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500",
        "https://img2.baidu.com/it/u=2365849672,3639725469&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=313",
        "https://img1.baidu.com/it/u=1894181349,2626000691&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        "https://img2.baidu.com/it/u=4038846864,3805332559&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800"
    )

    val listA = remember { mutableStateListOf("") }
    val listB = listOf<String>("123","234")
    val CoroutineScope = rememberCoroutineScope()

//----------------------界面-----------------------------//
        Column(Modifier.fillMaxSize()) {
            TapBar { Text("主页") }
            SwiperContent(swipeData = swipeData)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "欢迎使用zivcompose",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
//                    nav.navigate(RouterConst.firstView)
                    CoroutineScope.launch {
                        firstvm.httptest()
                    }

                          },
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(50)),
            ) {
                Text(
                    text = "创建账号",
                    style = MaterialTheme.typography.button,

                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
              modifier= Modifier
                  .fillMaxWidth()
                  .height(80.dp),
                horizontalArrangement = Arrangement.Center

            ) {
                SimpleDropDownMenu(
                    selectedIndex = firstvm.value5,
                    values = firstvm.list,
                    onChange = {
                        Log.d("====",firstvm.value5.toString())
                    }
                )
            }

            Loading()
        }


    }





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainViewPreview() {
    MainView()
}

