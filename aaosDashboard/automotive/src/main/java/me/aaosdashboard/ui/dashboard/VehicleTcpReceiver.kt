package me.aaosdashboard.ui.dashboard

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import me.aaosdashboard.BuildConfig
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket

class VehicleTcpReceiver(
    private val port: Int = BuildConfig.CANOE_RECEIVE_PORT,
    private val onReceive: (String) -> Unit
) {
    private var job: Job? = null

    fun start(scope: CoroutineScope) {
        job = scope.launch(Dispatchers.IO) {
            ServerSocket(port).use { serverSocket ->
                while (isActive) {
                    val socket = serverSocket.accept()

                    socket.use {
                        val reader = BufferedReader(
                            InputStreamReader(it.getInputStream())
                        )

                        var line: String?

                        while (reader.readLine().also { line = it } != null) {
                            line?.let { message ->
                                onReceive(message)
                            }
                        }
                    }
                }
            }
        }
    }

    fun stop() {
        job?.cancel()
    }
}