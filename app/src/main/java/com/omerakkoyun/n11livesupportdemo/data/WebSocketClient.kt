package com.omerakkoyun.n11livesupportdemo.data

import android.util.Log
import com.omerakkoyun.n11livesupportdemo.data.repository.WebSocketMessageListener
import com.omerakkoyun.n11livesupportdemo.utils.enums.ChatViewType
import com.omerakkoyun.n11livesupportdemo.utils.helpers.JsonHelper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */

class WebSocketClient @Inject constructor() {
    private lateinit var webSocket: WebSocket
    private var messageListener: WebSocketMessageListener? = null

    fun setMessageListener(listener: WebSocketMessageListener) {
        this.messageListener = listener
    }

    fun connect(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d("WebSocket", "Bağlantı açıldı")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d("WebSocket", "1- WebSocketClient -> onMessage veri geldi")
                if (!text.contains(ChatViewType.REQUEST_SERVED.typeName)){
                    messageListener?.onMessageReceived(JsonHelper.loadStepItemFromJson(text))
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e("WebSocket", "Hata: ${t.message}")
                messageListener?.onMessageError("Beklenmedik bir hata oluştu, lütfen daha sonra tekrar deneyin.")
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                // WebSocket bağlantısı kapandı
                Log.e("WebSocket", "WebSocket bağlantısı kapandı : ${reason}")
            }
        })
    }

    fun send(message: String) {
        if (::webSocket.isInitialized && webSocket.send(message)) {
            Log.d("WebSocket", "0. Mesaj gönderildi: ")
        } else {
            Log.e("WebSocket", "Mesaj gönderimi başarısız oldu!")
            messageListener?.onMessageError("Mesaj gönderimi başarısız oldu!")
        }
    }

    fun close() {
        webSocket.close(1000, "Closed..")
        Log.d("WebSocket", "Connection Closed")
    }
}