package com.example.zivcompose.ui.view

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zivcompose.ui.components.TapBar
import com.example.zivcompose.viewmodel.MainViewModel

var start = 1
@Composable
fun MainView() {

    val vm = MainViewModel()

    BoxWithConstraints {
        Column {
            TapBar { Text("主页") }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "Beautiful home garden solutions",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { },
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(50)),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = "Create account",
                    style = MaterialTheme.typography.button,

                )
            }
        }
        Log.d("===", "渲染次数： $start")

    }
}




@Preview
@Composable
fun MainViewPreview() {
    MainView()
}

