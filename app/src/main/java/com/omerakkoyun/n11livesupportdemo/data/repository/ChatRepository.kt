package com.omerakkoyun.n11livesupportdemo.data.repository

import com.omerakkoyun.n11livesupportdemo.data.WebSocketClient
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import com.omerakkoyun.n11livesupportdemo.domain.repository.IChatRepository
import com.omerakkoyun.n11livesupportdemo.utils.Constant.BASE_WEB_SOCKET_URL
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */
class ChatRepository @Inject constructor(private val webSocketClient: WebSocketClient) : IChatRepository, WebSocketMessageListener {

    private val _incomingMessagesFlow = MutableSharedFlow<StepItem?>(replay = 1)
    private val incomingMessagesFlow: SharedFlow<StepItem?> get() = _incomingMessagesFlow

    private val _messagesErrorFlow = MutableSharedFlow<String?>(replay = 1)
    private val messagesErrorFlow: SharedFlow<String?> get() = _messagesErrorFlow


    init {
        startConnection()
        webSocketClient.setMessageListener(this)
    }

    override fun getIncomingMessages(): SharedFlow<StepItem?> {
        return incomingMessagesFlow
    }

    override fun getErrorMessages(): SharedFlow<String?> {
        return messagesErrorFlow
    }

    override fun sendMessage(message: String) {
        webSocketClient.send(message)
    }

    override fun closeConnection() {
        webSocketClient.close()
    }

    override fun onMessageReceived(stepItem: StepItem) {
        _incomingMessagesFlow.tryEmit(stepItem)
    }

    override fun onMessageError(error: String) {
        _messagesErrorFlow.tryEmit(error)
    }

    override fun startConnection() {
        webSocketClient.connect(BASE_WEB_SOCKET_URL)
    }

}