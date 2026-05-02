package me.vehicledashboard.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun OtaCard(
    status: OtaStatus,
    progress: Int,
    isSmartLightExpanded: Boolean,
    onSmartLightClick: () -> Unit,
    onDownloadSmartLightClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2B2B2B)
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "업데이트 가능 목록",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (status != OtaStatus.SUCCESS) {
                SmartLightUpdateItem(
                    expanded = isSmartLightExpanded,
                    status = status,
                    progress = progress,
                    onClick = onSmartLightClick,
                    onDownloadClick = onDownloadSmartLightClick
                )
            }
        }
    }
}