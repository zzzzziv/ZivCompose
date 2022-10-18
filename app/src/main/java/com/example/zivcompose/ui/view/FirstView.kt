package com.example.zivcompose.ui.view


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.zivcompose.ui.components.TapBar
import com.example.zivcompose.viewmodel.FirstViewModel
import kotlinx.coroutines.launch

@Composable
fun FirstView() {

    //这样创建viewmodel 可以 让lifecycle 管理viewmodel的状态
    val firstvm:FirstViewModel = viewModel()
    val coroutine = rememberCoroutineScope() //用于挂起线程

//---------------------界面-------------------------//
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TapBar { Text("第一页") }

        Spacer(modifier = Modifier.height(40.dp))
        AsyncImage(
            model = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.doubanio.com%2Fview%2Fphoto%2Fsqs%2Fpublic%2Fp2677102402.jpg&refer=http%3A%2F%2Fimg2.doubanio.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1668696758&t=beb0eb8770d5132980893452cf825cc2",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp).clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(value =firstvm.username, onValueChange = {firstvm.username = it}, label ={ Text("用户名") })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value =firstvm.password, onValueChange = {firstvm.password = it}, label ={ Text("密码") })
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {
                         coroutine.launch {//挂起线程
                             firstvm.httptest()
                         }
        }, content = { Text("登录") })

        Text(text = firstvm.value1)
        Text(text = firstvm.value2)
        Text(text = firstvm.value3)
        Text(text = firstvm.value4)



    }


}

@Preview
@Composable
fun FirstViewPreview() {
    FirstView()
}

