package me.vehicledashboard.ui.dashboard

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

@Composable
fun BitmapProgressBar(
    @DrawableRes gifRes: Int,
    frameIndex: Int,
    frameCount: Int,
    scale: Float,
    offsetX: Int,
    offsetY: Int,
    modifier: Modifier = Modifier
) {
    val sheet = ImageBitmap.imageResource(id = gifRes)

    val frameWidth = sheet.width / frameCount
    val frameHeight = sheet.height

    Canvas(
        modifier = modifier.fillMaxSize()
    ) {
        drawSpriteFrame(
            sheet = sheet,
            frameIndex = frameIndex,
            frameWidth = frameWidth,
            frameHeight = frameHeight,
            canvasWidth = size.width,
            canvasHeight = size.height,
            scale = scale,
            offsetX = offsetX,
            offsetY = offsetY
        )
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
