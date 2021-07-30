package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.repository.INAERepository
import com.example.onem2m_in_ae.view.base.BaseViewModel

class ContainerRegisterViewModel(private val inAERepository: INAERepository): BaseViewModel() {
    val containerNameText = MutableLiveData<String>()

    init {
        containerNameText.value
    }

    fun containerRegister(containerImage: Int) = liveData {
        handle {
            inAERepository.registerContainerInstance(containerNameText.value, containerImage)
        }?.let {
            emit(it)
        }
    }
}