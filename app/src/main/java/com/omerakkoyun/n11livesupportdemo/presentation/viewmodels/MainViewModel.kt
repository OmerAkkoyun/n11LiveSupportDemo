package com.omerakkoyun.n11livesupportdemo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import com.omerakkoyun.n11livesupportdemo.domain.usecase.InsertStepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Omer AKKOYUN on 9.10.2024.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val insertUseCase: InsertStepUseCase) : ViewModel() {

    // Save All Steps,  (Room Database)
    fun insertAllSteps(list: List<StepItem>) {
        viewModelScope.launch {
            list.forEach { step ->
                insertUseCase(step)
            }
        }
    }
}




