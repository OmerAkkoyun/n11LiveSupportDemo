package com.omerakkoyun.n11livesupportdemo.di

import android.content.Context
import com.omerakkoyun.n11livesupportdemo.data.local.room.StepsDao
import com.omerakkoyun.n11livesupportdemo.data.local.room.StepsDatabase
import com.omerakkoyun.n11livesupportdemo.data.repository.StepsRepositoryImpl
import com.omerakkoyun.n11livesupportdemo.domain.repository.IStepsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */

@Module
@InstallIn(SingletonComponent::class)
object StepsDataModule {


    @Provides
    @Singleton
    fun provideStepsDatabase(@ApplicationContext context: Context): StepsDatabase {
        return StepsDatabase.getInstance(context)
    }

    @Provides
    fun provideStepsDao(stepsDatabase: StepsDatabase) : StepsDao {
        return stepsDatabase.getStepsDao()
    }

    @Provides
    fun provideStepRepository(stepsDao: StepsDao): IStepsRepository {
        return StepsRepositoryImpl(stepsDao)
    }


}