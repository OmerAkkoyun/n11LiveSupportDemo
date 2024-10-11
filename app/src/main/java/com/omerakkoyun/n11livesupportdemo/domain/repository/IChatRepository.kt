package com.omerakkoyun.n11livesupportdemo.domain.repository

import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import kotlinx.coroutines.flow.SharedFlow

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */
interface IChatRepository {
    fun sendMessage(message: String)
    fun getIncomingMessages(): SharedFlow<StepItem?>
    fun getErrorMessages(): SharedFlow<String?>
    fun closeConnection()
    fun startConnection()
}