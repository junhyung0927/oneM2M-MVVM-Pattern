package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_in_ae.repository.INAERepository
import com.example.onem2m_in_ae.view.base.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception

class AirConditionalViewModel(private val inAERepository: INAERepository) : BaseViewModel() {
    private val _contentInstanceInfo: MutableLiveData<ResponseCin> = MutableLiveData()
    val contentInstanceInfo: LiveData<ResponseCin> = _contentInstanceInfo
    fun getContentInstanceInfo(resourceName: String) {
        viewModelScope.launch {
            handle { inAERepository.getContentInstanceInfo(resourceName) }?.let {
                _contentInstanceInfo.value = it
            }
        }
    }

    private val _contentInstanceControl: MutableLiveData<Unit> = MutableLiveData()
    val contentInstanceControl: LiveData<Unit> = _contentInstanceControl
    fun deviceControl(content: String, resourceName: String) {
        viewModelScope.launch {
            handle { inAERepository.deviceControl(content, resourceName) }?.let {
                _contentInstanceControl.value = it
            }
        }
    }

    private val _deleteContainer: MutableLiveData<Unit> = MutableLiveData()
    val deleteContainer: LiveData<Unit> = _deleteContainer
    fun deleteDataBaseContainer(resourceName: String) {
        viewModelScope.launch {
            handle { inAERepository.deleteDatabaseContainer(resourceName) }?.let {
                _deleteContainer.value = it
            }
        }
    }

    val getContainerInfo = liveData<ResponseCnt> {
        handle { inAERepository.getContainerInfo() }?.let {
            emit(it)
        }
    }

    private val _createSub: MutableLiveData<Unit> = MutableLiveData()
    val createSub: LiveData<Unit> = _createSub
    fun createSubscription(resourceName: String){
        viewModelScope.launch {
            handle {
                inAERepository.createSubscription(resourceName) }?.let {
                _createSub.value = it
            }
        }
    }

    val getChildResourceInfo = liveData<ResponseCntUril> {
        handle { inAERepository.getChildResourceInfo()
        }?.let { emit(it) }
    }

    fun getResourceName(responseCntUril: ResponseCntUril): String {
        return responseCntUril.m2mUril
            .filter { it.startsWith("Mobius/IYAHN_DEMO/") }
            .find { it.contains("tvoc") }!!
            .split("/").last()
    }
}