package com.example.zivcompose.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "SwiperContent"

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwiperContent(
    swipeData:List<String>

) {

    //虚拟页数
    val virtualCount = 99999
    //实际页数
    val actualCount = swipeData.size

    //初始图片下标
    val initialIndex = virtualCount / 2 + (virtualCount / 2) % actualCount

    val pagerState = rememberPagerState(initialPage = initialIndex)
    val coroutineScope= rememberCoroutineScope()
    //控制轮播
    DisposableEffect(key1 = Unit, effect = {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }, 5000, 5000)
        onDispose {
            timer.cancel()
        }
    })


    HorizontalPager(
        count = virtualCount,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp)),
        state = pagerState
    ) { index ->

        val actualIndex = index % actualCount
        Box{
            AsyncImage(
                model = swipeData[actualIndex],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(7 / 3f),
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in swipeData.indices){
                    RadioButton(selected = actualIndex==i, onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(i)
                        }
                    })
                }
            }
        }



    }
}
