package com.omerakkoyun.n11livesupportdemo.data.repository

import com.omerakkoyun.n11livesupportdemo.data.local.room.StepEntity
import com.omerakkoyun.n11livesupportdemo.data.local.room.StepsDao
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import com.omerakkoyun.n11livesupportdemo.domain.repository.IStepsRepository
import com.omerakkoyun.n11livesupportdemo.utils.extensions.toStepEntity
import com.omerakkoyun.n11livesupportdemo.utils.extensions.toStepItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */
class StepsRepositoryImpl(private val stepsDao: StepsDao) : IStepsRepository {
    override suspend fun insert(stepItem: StepItem) {
        stepItem.toStepEntity()?.let { stepsDao.insertStep(it) }
    }

    override suspend fun getItemStepJsonByName(stepName: String): StepEntity {
      return  stepsDao.getStepByName(stepName)
    }

    override suspend fun delete(stepItem: StepItem) {
        stepItem.toStepEntity()?.let { stepsDao.deleteStep(it) }
    }

    override fun getAllSteps(): Flow<List<StepItem>> {
        return stepsDao.getAllSteps().map {
            it.map { stepEntity ->
                stepEntity.toStepItem()
            }
        }

    }
}