package com.omerakkoyun.n11livesupportdemo.domain.usecase

import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import com.omerakkoyun.n11livesupportdemo.domain.repository.IStepsRepository
import javax.inject.Inject

/**
 * Created by Omer AKKOYUN on 10.10.2024.
 */
class InsertStepUseCase @Inject constructor(private val repositoryImpl: IStepsRepository) {

    suspend operator fun invoke(step: StepItem) = repositoryImpl.insert(step)


}