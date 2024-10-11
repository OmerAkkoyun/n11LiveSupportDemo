package com.omerakkoyun.n11livesupportdemo.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omerakkoyun.n11livesupportdemo.data.models.StepItem
import com.omerakkoyun.n11livesupportdemo.domain.repository.IChatRepository
import com.omerakkoyun.n11livesupportdemo.domain.repository.IStepsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Omer AKKOYUN on 9.10.2024.
 */
@HiltViewModel
class ChatViewModel @Inject constructor(private val chatRepository: IChatRepository, private val stepsRepository: IStepsRepository) : ViewModel() {

    private var _incomingMessagesLiveData = MutableLiveData<StepItem?>()
    val incomingMessagesLiveData: LiveData<StepItem?> = _incomingMessagesLiveData

    private var _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> = _errorLiveData


    init {
        observeIncomingMessages()
    }

    private fun observeIncomingMessages() {
        viewModelScope.launch {
            // flow + livedata
            chatRepository.getIncomingMessages().collect { jsonMessage ->
                _incomingMessagesLiveData.postValue(jsonMessage)
            }
        }

        viewModelScope.launch {
            chatRepository.getErrorMessages().collect { errorMessage ->
                _errorLiveData.postValue(errorMessage)
            }
        }
    }

    fun getNextStepFromWebSocket(stepName: String) {
        //  Send message to WebSockett
        var jsonMessage = ""
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                jsonMessage  =  stepsRepository.getItemStepJsonByName(stepName).stepContentJson
            }
            chatRepository.sendMessage(jsonMessage)
        }
    }

    fun closeConnection() {
        chatRepository.closeConnection()
    }
}




