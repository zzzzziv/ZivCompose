package com.example.zivcompose.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val appBarHeight = 45.dp
//标题栏高度
@Composable
fun TapBar(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colors.primary
                )

            .statusBarsPadding()
            .padding(top = 5.dp)
            .height(appBarHeight)
            .then(modifier),
        horizontalArrangement = Arrangement.Center,

    ) {
        content()
    }

}


@Preview
@Composable
fun TapBarPreview() {
    TapBar(){
        Text("标题")
    }
}

