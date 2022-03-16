package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.onem2m_in_ae.domain.usecase.container.*
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_in_ae.view.base.BaseViewModel
import kotlinx.coroutines.launch

class ContainerViewModel(
    private val getContentInstanceInfoUseCase: GetContentInstanceInfoUseCase,
    private val deviceControlUseCase: DeviceControlUseCase,
    private val deleteContainerUseCase: DeleteContainerUseCase,
    private val getContainerInfoUseCase: GetContainerInfoUseCase,
    private val createSubscriptionUseCase: CreateSubscriptionUseCase,
    private val getChildResourceInfoUseCase: GetChildResourceInfoUseCase,
) : BaseViewModel() {
    private val _contentInstanceInfo: MutableLiveData<ResponseCin> = MutableLiveData()
    val contentInstanceInfo: LiveData<ResponseCin> = _contentInstanceInfo
    fun getContentInstanceInfo(resourceName: String) {
        viewModelScope.launch {
            handle {
                getContentInstanceInfoUseCase.invoke(resourceName).let {
                    it.onSuccess {
                        _contentInstanceInfo.value = it
                    }
                }
            }
        }
    }

    private val _contentInstanceControl: MutableLiveData<Unit> = MutableLiveData()
    val contentInstanceControl: LiveData<Unit> = _contentInstanceControl
    fun deviceControl(content: String, resourceName: String) {
        val controlInfo = mapOf(
            "content" to content,
            "resourceName" to resourceName
        )
        viewModelScope.launch {
            deviceControlUseCase.invoke(controlInfo).let {
                it.onSuccess {
                    _contentInstanceControl.value = it
                }
            }
        }
    }

    private val _deleteContainer: MutableLiveData<Unit> = MutableLiveData()
    val deleteContainer: LiveData<Unit> = _deleteContainer
    fun deleteContainer(resourceName: String) {
        viewModelScope.launch {
            handle {
                deleteContainerUseCase.invoke(resourceName).let {
                    it.onSuccess {
                        _deleteContainer.value = it
                    }
                }
            }
        }
    }

    val getContainerInfo = liveData<ResponseCnt> {
        handle {
            getContainerInfoUseCase.invoke().let {
                it.onSuccess {
                    emit(it)
                }
            }
        }
    }

    private val _createSub: MutableLiveData<Unit> = MutableLiveData()
    val createSub: LiveData<Unit> = _createSub
    fun createSubscription(resourceName: String) {
        viewModelScope.launch {
            handle {
                createSubscriptionUseCase.invoke(resourceName).let {
                    it.onSuccess {
                        _createSub.value = it
                    }
                }
            }
        }
    }

    val getChildResourceInfo = liveData<ResponseCntUril> {
        handle {
            getChildResourceInfoUseCase.invoke().let {
                it.onSuccess {
                    emit(it)
                }
            }
        }
    }

    fun getResourceName(responseCntUril: ResponseCntUril, containerName: String): String {
        val name = when (containerName) {
            "airconditioner" -> "airconditioner"
            "airpurifer" -> "airpurifer"
            "boiler" -> "boiler"
            else -> "none"
        }

        return responseCntUril.m2mUril
            .filter { it.startsWith("Mobius/IYAHN_DEMO/") }
            .find { it.contains(name) }!!
            .split("/").last()
    }
}