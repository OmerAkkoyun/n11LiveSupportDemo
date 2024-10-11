package com.omerakkoyun.n11livesupportdemo.domain.repository

import com.omerakkoyun.n11livesupportdemo.data.local.room.StepEntity
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */
interface IStepsRepository {

    suspend fun insert(stepItem: StepItem)
    suspend fun getItemStepJsonByName(stepName: String): StepEntity
    suspend fun delete(stepItem: StepItem)
    fun getAllSteps(): Flow<List<StepItem>>

}