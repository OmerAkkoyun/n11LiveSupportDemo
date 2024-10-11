package com.omerakkoyun.n11livesupportdemo.data.repository

import com.omerakkoyun.n11livesupportdemo.data.models.StepItem

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */
interface WebSocketMessageListener {
    fun onMessageReceived(stepItem: StepItem)
    fun onMessageError(error: String)
}