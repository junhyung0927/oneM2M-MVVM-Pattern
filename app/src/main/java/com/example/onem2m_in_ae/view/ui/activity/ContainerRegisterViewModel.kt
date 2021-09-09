package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onem2m_in_ae.model.DeviceType
import com.example.onem2m_in_ae.repository.OneM2MRepository
import com.example.onem2m_in_ae.view.base.BaseViewModel
import kotlinx.coroutines.launch

class ContainerRegisterViewModel(private val oneM2MRepository: OneM2MRepository): BaseViewModel() {
    private val _containerRegister: MutableLiveData<Unit> = MutableLiveData()
    val containerRegister: LiveData<Unit> = _containerRegister

    fun registerContainerInstance(image: Int, name: String, type: DeviceType) {
        viewModelScope.launch {
            handle { oneM2MRepository.registerContainerInstance(name, image, type) }?.let {
                _containerRegister.value = it
            }
        }
    }
}