package me.aaosdashboard.ui.dashboard

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.aaosdashboard.BuildConfig
import java.net.Socket

class CanoeTcpSender(
    private val host: String = BuildConfig.CANOE_SEND_HOST,
    private val port: Int = BuildConfig.CANOE_SEND_PORT
) {
    suspend fun send(message: String) {
        withContext(Dispatchers.IO) {
            Socket(host, port).use { socket ->
                socket.getOutputStream().write(
                    (message + "\n").toByteArray()
                )
                socket.getOutputStream().flush()
            }
        }
    }
}