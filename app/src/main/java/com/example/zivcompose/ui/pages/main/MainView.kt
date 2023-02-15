package com.example.zivcompose.ui.pages.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zivcompose.ui.components.TapBar
import com.example.zivcompose.ui.navcaiton.NewNavHost
import com.example.zivcompose.ui.navcaiton.RouterConst


@Composable
fun MainView() {

    val nav: NewNavHost = viewModel()
    var bColor by remember {
        mutableStateOf(Color.Gray)
    }
    var currChoose by remember {
        mutableStateOf("")
    }



//----------------------界面-----------------------------//
        Column(Modifier.fillMaxSize()) {
            TapBar { Text("主页") }
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
                    nav.navigate(RouterConst.firstView)
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


        }


    }





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainViewPreview() {
    MainView()
}

