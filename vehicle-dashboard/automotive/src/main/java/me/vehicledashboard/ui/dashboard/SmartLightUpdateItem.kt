package me.vehicledashboard.ui.dashboard

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.vehicledashboard.R

@Composable
fun SmartLightUpdateItem(
    expanded: Boolean,
    status: OtaStatus,
    progress: Int,
    onClick: () -> Unit,
    onDownloadClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Smart Light",
            modifier = Modifier
                .padding(vertical = 8.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { onClick() }
                    )
                },
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        if (expanded) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF101010)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Smart Light",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "추가되는 기능",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFBDBDBD)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("- 코너링 라이트", color = Color.White)
                    Text("- 오토 빔", color = Color.White)

                    Spacer(modifier = Modifier.height(24.dp))

                    if (status == OtaStatus.DOWNLOADING) {
                        BitmapProgressBar(
                            gifRes = R.drawable.progress_bar,
                            frameIndex = progress,
                            frameCount = 11,
                            scale = 0.4f,
                            offsetX = 0,
                            offsetY = 0,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        Button(
                            onClick = onDownloadClick,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color(0xFF101010)
                            )
                        ) {
                            Text("다운로드", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}