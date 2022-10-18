package com.example.zivcompose.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zivcompose.R

val appBarHeight = 60.dp
//标题栏高度
@Composable
fun TapBar(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    BoxWithConstraints(
        modifier = Modifier.height(appBarHeight)
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)) {
        Image(painter = painterResource(R.drawable.texture) ,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 5.dp, start = 5.dp)
                .fillMaxHeight()
                .then(modifier),
            horizontalArrangement = Arrangement.Center,

            ) {
            content()
        }
    }


}


@Preview
@Composable
fun TapBarPreview() {
    TapBar(){
        Text("标题")
    }
}

