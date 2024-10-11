package com.omerakkoyun.n11livesupportdemo.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omerakkoyun.n11livesupportdemo.utils.Constant.STEPS_DATABASE_NAME


/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */

@Database(entities = [StepEntity::class], version = 1)
abstract class StepsDatabase : RoomDatabase() {

    companion object{
        fun getInstance(context: Context): StepsDatabase {
           return Room.databaseBuilder(context, StepsDatabase::class.java, STEPS_DATABASE_NAME)
               .build()
        }
    }

    abstract fun getStepsDao(): StepsDao

}