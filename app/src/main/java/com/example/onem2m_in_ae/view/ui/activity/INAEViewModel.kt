package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.view.base.BaseViewModel
import com.example.onem2m_in_ae.repository.OneM2MRepository
import androidx.lifecycle.liveData
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.util.Event

class INAEViewModel(private val oneM2MRepository: OneM2MRepository) : BaseViewModel() {
    val createAE = liveData<Unit> {
        handle { oneM2MRepository.createAE() }?.let {
            emit(it)
        }
    }

    val getContainerDatabase = liveData<List<ContainerInstance>> {
        handle { oneM2MRepository.getContentInstanceDatabase() }?.let {
            emit(it)
        }
    }

    val getAEInfo = liveData<ResponseAE> {
        handle { oneM2MRepository.getAEInfo() }?.let {
            emit(it)
        }
    }

    private val _onDeviceItemEvent = MutableLiveData<Event<ContainerInstance>>()
    val onDeviceItemEvent: LiveData<Event<ContainerInstance>>
        get() = _onDeviceItemEvent

    fun callOnDeviceImageEvent(deviceItem: ContainerInstance) {
        _onDeviceItemEvent.value = Event(deviceItem)
    }
}


