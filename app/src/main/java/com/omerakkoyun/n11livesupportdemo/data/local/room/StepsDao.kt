package com.omerakkoyun.n11livesupportdemo.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */

@Dao
interface StepsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStep(stepEntity: StepEntity): Long

    @Update
    suspend fun updateStep(stepEntity: StepEntity): Int


    @Delete
    suspend fun deleteStep(stepEntity: StepEntity): Int

    @Query ("SELECT * FROM step_table WHERE step = :stepName")
    fun getStepByName(stepName: String): StepEntity

    @Query("SELECT * FROM step_table")
    fun getAllSteps(): Flow<List<StepEntity>>


}