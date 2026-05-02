package me.vehicledashboard.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.vehicledashboard.R

@Composable
fun SpeedGauge(
    speed: Int,
    maxSpeed: Int,
    modifier: Modifier = Modifier
) {
    val clampedSpeed = speed.coerceIn(0, maxSpeed)

    val bgPainter = painterResource(id = R.drawable.speedgauge)

    val frameCount = 13

    val frameIndex = calculateNeedleIndex(
        speed = clampedSpeed,
        maxSpeed = maxSpeed,
        frameCount = frameCount
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = bgPainter,
                contentDescription = "계기판 배경",
                modifier = Modifier
                    .fillMaxSize(0.65f)
                    .offset(y = 5.dp),
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        bottom = 63.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = clampedSpeed.toString(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00FF67)
                )
            }

            BitmapProgressBar(
                gifRes = R.drawable.speedgauge_line_list,
                frameIndex = frameIndex,
                frameCount = frameCount,
                scale = 0.6f,
                offsetX = 0,
                offsetY = -14,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

private fun calculateNeedleIndex(
    speed: Int,
    maxSpeed: Int,
    frameCount: Int
): Int {
    if (frameCount <= 1 || maxSpeed <= 0) return 0

    val ratio = speed.toFloat() / maxSpeed.toFloat()
    return (ratio * (frameCount - 1))
        .toInt()
        .coerceIn(0, frameCount - 1)
}