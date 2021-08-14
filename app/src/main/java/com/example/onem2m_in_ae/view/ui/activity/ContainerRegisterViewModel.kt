package com.example.onem2m_in_ae.view.ui.activity

import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_in_ae.repository.INAERepository
import com.example.onem2m_in_ae.view.base.BaseViewModel
import kotlinx.coroutines.launch

class ContainerRegisterViewModel(private val inAERepository: INAERepository): BaseViewModel() {
    private val _containerRegister: MutableLiveData<Unit> = MutableLiveData()
    val containerRegister: LiveData<Unit> = _containerRegister

    fun registerContainerInstance(image: Int, name: String, type: ContainerType) {
        viewModelScope.launch {
            handle { inAERepository.registerContainerInstance(name,image, type) }?.let {
                _containerRegister.value = it
            }
        }
    }
}