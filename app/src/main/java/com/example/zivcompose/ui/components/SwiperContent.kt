package com.example.zivcompose.ui.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
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
    val virtualCount = Int.MAX_VALUE
    //实际页数
    val actualCount = swipeData.size

    //初始图片下标
    val initialIndex = virtualCount / 2 + (virtualCount / 2) % actualCount
    // 页码转换
    fun pageMapper(index: Int) = (index - initialIndex).floorMod(actualCount)

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

    Box{
        HorizontalPager(
            count = virtualCount,
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            state = pagerState
        ) { index ->
            val actualIndex = index % actualCount
            AsyncImage(
                model = swipeData[actualIndex],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(7 / 3f),
                contentScale = ContentScale.Crop,
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = White,
            inactiveColor = LightGray,
            indicatorWidth = 10.dp,
            indicatorHeight = 4.dp,
            spacing = 5.dp,
            pageCount = actualCount,
            pageIndexMapping = ::pageMapper,
            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
                .padding(bottom = 15.dp)
        )

    }

}
/**
 * 转换下标
 */
private fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}