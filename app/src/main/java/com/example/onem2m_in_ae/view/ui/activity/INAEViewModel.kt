package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.view.base.BaseViewModel
import com.example.onem2m_in_ae.repository.INAERepository
import androidx.lifecycle.liveData
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.util.Event
import retrofit2.HttpException
import java.lang.Exception

class INAEViewModel(private val inAERepository: INAERepository) : BaseViewModel() {
    val createAE = liveData<Unit> {
        handle { inAERepository.createAE() }?.let {
            emit(it)
        }
    }

    val getContainerDatabase = liveData<List<ContainerInstance>> {
        handle { inAERepository.getContentInstanceDatabase() }?.let {
            emit(it)
        }
    }

    val getAEInfo = liveData<ResponseAE> {
        handle { inAERepository.getAEInfo() }?.let {
            emit(it)
        }
    }

    private val _onContainerItemEvent = MutableLiveData<Event<ContainerInstance>>()
    val onContainerItemEvent: LiveData<Event<ContainerInstance>>
        get() = _onContainerItemEvent

    fun callOnContainerImageEvent(containerItem: ContainerInstance) {
        _onContainerItemEvent.value = Event(containerItem)
    }
}


