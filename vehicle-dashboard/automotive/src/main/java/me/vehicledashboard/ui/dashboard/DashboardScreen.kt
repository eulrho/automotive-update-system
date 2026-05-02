package me.vehicledashboard.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DashboardRoute(
    viewModel: DashboardViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    DashboardScreen(
        uiState = uiState,
        onSmartLightClick = viewModel::onSmartLightClick,
        onDownloadSmartLightClick = viewModel::onDownloadSmartLightClick
    )
}

@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    onSmartLightClick: () -> Unit,
    onDownloadSmartLightClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (uiState.ignitionState == IgnitionState.OFF) {
        PowerOffScreen()
        return
    }
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(24.dp)
        ) {
            HeadBar(
                vehicleName = uiState.vehicleName,
                date = uiState.date,
                time = uiState.time
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ClusterArea(
                    speedKmh = uiState.speedKmh,
                    maxSpeedKmh = uiState.maxSpeedKmh,
                    modifier = Modifier
                        .weight(0.77f)
                        .fillMaxHeight(),
                    ignitionState = uiState.ignitionState
                )

                OtaCard(
                    status = uiState.otaStatus,
                    progress = uiState.otaProgressFrameIndex,
                    isSmartLightExpanded = uiState.isSmartLightExpanded,
                    onSmartLightClick = onSmartLightClick,
                    onDownloadSmartLightClick = onDownloadSmartLightClick,
                    modifier = Modifier
                        .weight(0.23f)
                        .fillMaxHeight()
                )
            }
        }
    }
}

