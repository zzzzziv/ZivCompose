package com.example.zivcompose.ui.view


import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FirstView() {

    Button(onClick = {}){
        Text("点击")
    }
}

@Preview
@Composable
fun FirstViewPreview() {
    FirstView()
}

