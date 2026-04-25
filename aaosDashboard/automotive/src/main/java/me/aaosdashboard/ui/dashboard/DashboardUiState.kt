package me.aaosdashboard.ui.dashboard

enum class IgnitionState {
    OFF,
    ACC,
    ON
}

data class DashboardUiState(
    val vehicleName: String = "NoBrake Demo Car",
    val isConnected: Boolean = true,
    val date: String = "2026.04.26",
    val time: String = "PM 04:54",
    val currentVersion: String = "1.0",
    val latestVersion: String = "1.0",
    val speedKmh: Int = 0,
    val maxSpeedKmh: Int = 240,
    val otaStatus: OtaStatus = OtaStatus.DOWNLOADING,
    val otaProgress: Int = 45,
    val otaMessage: String = "업데이트 파일을 다운로드하는 중입니다.",
    val ignitionState: IgnitionState = IgnitionState.ACC
)