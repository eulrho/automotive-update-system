package me.aaosdashboard.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HeadBar(
    vehicleName: String,
    date: String,
    time: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF101010)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HeadText(vehicleName, MaterialTheme.typography.headlineSmall)

            Column(
                horizontalAlignment = Alignment.End
            ) {
                HeadText(date, MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(6.dp))

                HeadText(time, MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
private fun HeadText(
    content: String,
    style: TextStyle
) {
    Text(
        text = content,
        style = style,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}