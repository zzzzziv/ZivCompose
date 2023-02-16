package com.example.zivcompose.ui.components



import android.graphics.RectF
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.example.zivcompose.ui.pages.main.MainView
import kotlin.math.PI
import kotlin.math.atan2

@Composable
fun Loading() {
    val infiniteTransition = rememberInfiniteTransition()

    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1500
                0f at 0
                10f at 725
                0f at 1500
            }
        )
    )
    val animatePer by infiniteTransition.animateFloat(initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = keyframes {
            durationMillis = 1300
            0f at 0
            1f at 1300
        }
        ))

    val paint = Paint().asFrameworkPaint()

    Box(Modifier.fillMaxSize(1f)){
        Box(Modifier.align(Alignment.Center)) {
            Canvas(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = LocalDensity.current.run { offsetY.toDp() }) // 使用动画
            ) {
                drawCircle(
                    color = Color.Gray, radius = 50f, style = Stroke(width = 10f)
                )
            }


            Canvas(
                modifier = Modifier.align(Alignment.Center)
            ) {
                val path = Path()
                val pathMeasure = android.graphics.PathMeasure()
                val pos = FloatArray(2)
                val tan = FloatArray(2)
                path.addOval(Rect(-75f, -35f, 75f, 35f))
                rotate(-20f) {
                    pathMeasure.setPath(path.asAndroidPath(), true)
                    pathMeasure.getPosTan(pathMeasure.length * animatePer, pos, tan)
                    paint.apply {
                        isAntiAlias = true
                        strokeWidth = 25f
                        strokeCap = android.graphics.Paint.Cap.ROUND
                        style = android.graphics.Paint.Style.STROKE
                        // 增加渐变
                        shader = android.graphics.RadialGradient(
                            pos[0],
                            pos[1],
                            80f,
                            android.graphics.Color.parseColor("#666466"),
                            android.graphics.Color.parseColor("#e5e3e5"),
                            android.graphics.Shader.TileMode.CLAMP
                        )
                    }

                    drawIntoCanvas {
                        it.nativeCanvas.drawArc(
                            RectF(-80f, -20f, 80f, 20f),
                            (atan2(pos[1], pos[0])) * 180 / PI.toFloat(),//
                            20f,// 调整SweepAngle 即可改变长度
                            false,
                            paint
                        )
                    }
                }
            }


        }

    }

}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingPreview() {
    Loading()
}