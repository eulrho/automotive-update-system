package me.aaosdashboard.ui.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.aaosdashboard.R

@Composable
fun VehicleImageArea(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        GifImage(
            gifRes = R.drawable.vehicle,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "차량 이미지"
        )
    }
}