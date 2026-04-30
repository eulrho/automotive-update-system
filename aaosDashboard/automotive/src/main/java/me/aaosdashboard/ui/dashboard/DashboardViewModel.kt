package me.aaosdashboard.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class OtaStatus(val label: String) {
    IDLE("대기 중"),
    CHECKING("업데이트 확인 중"),
    AVAILABLE("업데이트 가능"),
    DOWNLOADING("다운로드 중"),
    SUCCESS("업데이트 완료"),
    FAILED("업데이트 실패")
}

class DashboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun onSmartLightClick() {
        _uiState.update { state ->
            state.copy(
                isSmartLightExpanded = !state.isSmartLightExpanded
            )
        }
    }

    fun onDownloadSmartLightClick() {
//        _uiState.update { state ->
//            state.copy(
//                otaProgressFrameIndex = index.coerceIn(0, 10)
//            )
//        }
        // 테스트용
        _uiState.update { state ->
            state.copy(
                otaStatus = OtaStatus.DOWNLOADING,
                otaProgressFrameIndex = 0
            )
        }

        viewModelScope.launch {

            for (frameIndex in 0..10) {
                delay(500)
                _uiState.update { state ->
                    state.copy(
                        otaProgressFrameIndex = frameIndex
                    )
                }
            }

            delay(500)
            _uiState.update { state ->
                state.copy(
                    otaStatus = OtaStatus.SUCCESS,
                    isSmartLightExpanded = false
                )
            }
        }
    }

    fun updateSpeed(speed: Int) {
        _uiState.value = _uiState.value.copy(
            speedKmh = speed.coerceAtLeast(0)
        )
    }

    fun updateProgressFrameIndex(progress: Int) {
        _uiState.value = _uiState.value.copy(
            otaProgressFrameIndex = progress.coerceIn(0, 100)
        )
    }
}