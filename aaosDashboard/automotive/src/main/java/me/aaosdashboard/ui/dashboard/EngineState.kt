package me.aaosdashboard.ui.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.aaosdashboard.R

@Composable
fun EngineState(
    ignitionState: IgnitionState,
    modifier: Modifier = Modifier
) {
    val bgPainter = painterResource(id = R.drawable.state)
    val lineSheet = ImageBitmap.imageResource(id = R.drawable.state_line_list)

    val frameCount = 2
    val frameWidth = lineSheet.width / frameCount
    val frameHeight = lineSheet.height
    val frameIndex = when (ignitionState) {
        IgnitionState.ACC -> 0
        IgnitionState.ON -> 1
    }

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
                contentDescription = "엔진 상태 배경",
                modifier = Modifier.fillMaxSize(0.8f),
                contentScale = ContentScale.Fit
            )

            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                drawSpriteFrame(
                    sheet = lineSheet,
                    frameIndex = frameIndex,
                    frameWidth = frameWidth,
                    frameHeight = frameHeight,
                    canvasWidth = size.width,
                    canvasHeight = size.height,
                    scale = 1.3f,
                    offsetX = 0,
                    offsetY = 6
                )
            }
        }
    }
}

private fun DrawScope.drawSpriteFrame(
    sheet: ImageBitmap,
    frameIndex: Int,
    frameWidth: Int,
    frameHeight: Int,
    canvasWidth: Float,
    canvasHeight: Float,
    scale: Float,
    offsetX: Int,
    offsetY: Int
) {
    val dstWidth = (frameWidth * scale).toInt()
    val dstHeight = (frameHeight * scale).toInt()

    val dstOffsetX = ((canvasWidth - dstWidth) / 2f).toInt() + offsetX
    val dstOffsetY = ((canvasHeight - dstHeight) / 2f).toInt() + offsetY

    drawImage(
        image = sheet,
        srcOffset = IntOffset(
            x = frameIndex * frameWidth,
            y = 0
        ),
        srcSize = IntSize(
            width = frameWidth,
            height = frameHeight
        ),
        dstOffset = IntOffset(dstOffsetX, dstOffsetY),
        dstSize = IntSize(dstWidth, dstHeight),
        filterQuality = FilterQuality.High
    )
}