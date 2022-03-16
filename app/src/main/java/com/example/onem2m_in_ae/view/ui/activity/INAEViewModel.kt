package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.view.base.BaseViewModel
import androidx.lifecycle.liveData
import com.example.onem2m_in_ae.domain.usecase.inae.CreateAEUseCase
import com.example.onem2m_in_ae.domain.usecase.inae.GetAEInfoUseCase
import com.example.onem2m_in_ae.domain.usecase.inae.GetContainerInstanceInfoListUseCase
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.util.Event

class INAEViewModel(
    private val createAEUseCase: CreateAEUseCase,
    private val getAEInfoUseCase: GetAEInfoUseCase,
    private val getContainerInstanceInfoListUseCase: GetContainerInstanceInfoListUseCase
) : BaseViewModel() {
    val createAE = liveData<Unit> {
        handle { createAEUseCase.invoke() }?.let {
            it.onSuccess {
                emit(it)
            }
        }
    }

    val getContainerInstanceInfoList = liveData<List<ContainerInstance>> {
        handle { getContainerInstanceInfoListUseCase.invoke() }?.let {
            it.onSuccess {
                emit(it)
            }
        }
    }

    val getAEInfo = liveData<ResponseAE> {
        handle { getAEInfoUseCase.invoke() }?.let {
            it.onSuccess {
                emit(it)
            }
        }
    }

    private val _onDeviceItemEvent = MutableLiveData<Event<ContainerInstance>>()
    val onDeviceItemEvent: LiveData<Event<ContainerInstance>>
        get() = _onDeviceItemEvent

    fun callOnDeviceImageEvent(deviceItem: ContainerInstance) {
        _onDeviceItemEvent.value = Event(deviceItem)
    }
}


