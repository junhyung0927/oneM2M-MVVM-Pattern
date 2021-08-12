package com.example.onem2m_in_ae.view.ui.activity

import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_in_ae.repository.INAERepository
import com.example.onem2m_in_ae.view.base.BaseViewModel

class ContainerRegisterViewModel(private val inAERepository: INAERepository): BaseViewModel() {
    fun containerRegister(image: Int, name: String, type: ContainerType) = liveData {
        handle {
            inAERepository.registerContainerInstance(name, image, type)
        }?.let { emit(it) }
    }

    fun createContainer(name: String) = liveData {
        handle { inAERepository.createContainer(name)
        }?.let { emit(it) }
    }

    val getChildResourceInfo = liveData<ResponseCntUril> {
        handle { inAERepository.getChildResourceInfo()
        }?.let { emit(it) }
    }

}