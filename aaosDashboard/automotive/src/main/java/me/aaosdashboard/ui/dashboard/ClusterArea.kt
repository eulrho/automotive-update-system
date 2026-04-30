package me.aaosdashboard.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp

@Composable
fun ClusterArea(
    speedKmh: Int,
    maxSpeedKmh: Int,
    modifier: Modifier = Modifier,
    ignitionState: IgnitionState
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2B2B2B)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.23f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    SpeedGauge(
                        speed = speedKmh,
                        maxSpeed = maxSpeedKmh,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    EngineState(
                        ignitionState = ignitionState,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Card(
                modifier = Modifier
                    .weight(0.77f)
                    .fillMaxHeight(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                VehicleImageArea(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}