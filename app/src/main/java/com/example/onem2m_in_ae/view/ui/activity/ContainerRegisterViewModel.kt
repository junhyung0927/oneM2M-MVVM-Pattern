package com.example.onem2m_in_ae.view.ui.activity

import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.onem2m_in_ae.repository.INAERepository
import com.example.onem2m_in_ae.view.base.BaseViewModel

class ContainerRegisterViewModel(private val inAERepository: INAERepository): BaseViewModel() {
    fun containerRegister(image: Int, name: String) = liveData {
        handle {
            inAERepository.registerContainerInstance(name, image)
        }?.let {
            emit(it)
            println("테스트: ${it}")
        }
    }
}