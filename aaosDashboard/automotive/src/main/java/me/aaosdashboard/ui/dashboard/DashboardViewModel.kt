package me.aaosdashboard.ui.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class OtaStatus(val label: String) {
    IDLE("대기 중"),
    CHECKING("업데이트 확인 중"),
    AVAILABLE("업데이트 가능"),
    DOWNLOADING("다운로드 중"),
    INSTALLING("설치 중"),
    SUCCESS("업데이트 완료"),
    FAILED("업데이트 실패")
}

class DashboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun onCheckUpdateClick() {
        _uiState.value = _uiState.value.copy(
            otaStatus = OtaStatus.CHECKING,
            otaMessage = "최신 버전을 확인하는 중입니다."
        )
    }

    fun onStartUpdateClick() {
        _uiState.value = _uiState.value.copy(
            otaStatus = OtaStatus.DOWNLOADING,
            otaProgress = 0,
            otaMessage = "업데이트 다운로드를 시작했습니다."
        )
    }

    fun onInstallClick() {
        _uiState.value = _uiState.value.copy(
            otaStatus = OtaStatus.INSTALLING,
            otaMessage = "업데이트 설치를 진행하는 중입니다."
        )
    }

    fun onRetryClick() {
        _uiState.value = _uiState.value.copy(
            otaStatus = OtaStatus.AVAILABLE,
            otaProgress = 0,
            otaMessage = "업데이트를 다시 시도할 수 있습니다."
        )
    }

    fun updateSpeed(speed: Int) {
        _uiState.value = _uiState.value.copy(
            speedKmh = speed.coerceAtLeast(0)
        )
    }

    fun updateProgress(progress: Int) {
        _uiState.value = _uiState.value.copy(
            otaProgress = progress.coerceIn(0, 100)
        )
    }
}