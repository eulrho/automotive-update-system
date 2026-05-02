package me.vehicledashboard.ui.dashboard

import me.vehicledashboard.BuildConfig
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
        _uiState.update { state ->
            state.copy(
                otaStatus = OtaStatus.DOWNLOADING,
                otaProgressFrameIndex = 0
            )
        }

        viewModelScope.launch {
            canoeSender.send("command=download;target=smart_light")
        }
    }

    private fun updateProgressFrameIndex(index: Int) {
        val safeIndex = index.coerceIn(0, 10)

        _uiState.update { state ->
            state.copy(
                otaProgressFrameIndex = safeIndex
            )
        }

        if (
            safeIndex == 10 &&
            _uiState.value.otaStatus != OtaStatus.SUCCESS
        ) {
            viewModelScope.launch {
                delay(1000)

                _uiState.update { state ->
                    state.copy(
                        otaStatus = OtaStatus.SUCCESS,
                        isSmartLightExpanded = false
                    )
                }
            }
        }
    }

    // canoe - receive
    private val receiver = VehicleTcpReceiver(
        port = 5000,
        onReceive = { message ->
            handleVehicleMessage(message)
        }
    )

    init {
//        Log.d("DashboardViewModel", "receivePort=${BuildConfig.CANOE_RECEIVE_PORT}")
//        Log.d("DashboardViewModel", "sendHost=${BuildConfig.CANOE_SEND_HOST}")
//        Log.d("DashboardViewModel", "sendPort=${BuildConfig.CANOE_SEND_PORT}")
        receiver.start(viewModelScope)
    }

    private fun handleVehicleMessage(message: String) {
//        Log.d("DashboardViewModel", "value=${message}")
        val values = message
            .split(";")
            .mapNotNull {
                val parts = it.split("=")
                if (parts.size == 2) parts[0] to parts[1]
                else null
            }
            .toMap()

        val speed = values["speed"]?.toIntOrNull()

        val ignition = when (
            values["ignition_state"]?.toIntOrNull()
        ) {
            0 -> IgnitionState.OFF
            1 -> IgnitionState.ACC
            2 -> IgnitionState.ON
            else -> null
        }
        val progressIndex = values["progress_state_idx"]?.toIntOrNull()

        val date = values["date"];
        val time = values["time"];

        _uiState.update { state ->
            state.copy(
                speedKmh = speed ?: state.speedKmh,
                ignitionState = ignition ?: state.ignitionState,
                date = date ?: state.date,
                time = time ?: state.time
            )
        }

        progressIndex?.let {
            updateProgressFrameIndex(it)
        }
    }

    override fun onCleared() {
        receiver.stop()
        super.onCleared()
    }

    // canoe - sender
    private val canoeSender = CanoeTcpSender(
        host = BuildConfig.CANOE_SEND_HOST,
        port = BuildConfig.CANOE_SEND_PORT
    )
}