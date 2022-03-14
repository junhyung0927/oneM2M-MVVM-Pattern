package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.onem2m_in_ae.domain.usecase.container.RegisterContainerInstanceUseCase
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.DeviceType
import com.example.onem2m_in_ae.repository.OneM2MRepository
import com.example.onem2m_in_ae.view.base.BaseViewModel
import kotlinx.coroutines.launch

class ContainerRegisterViewModel(
    private val registerContainerInstanceUseCase: RegisterContainerInstanceUseCase
) : BaseViewModel() {
    private val _containerRegister: MutableLiveData<Unit> = MutableLiveData()
    val containerRegister: LiveData<Unit> = _containerRegister

    fun registerContainerInstance(image: Int, name: String, type: DeviceType) {
        val containerInstance = ContainerInstance(
            deviceImage = image,
            deviceName = name,
            deviceType = type
        )

        viewModelScope.launch {
            handle { registerContainerInstanceUseCase.invoke(containerInstance) }?.let {
                it.onSuccess { _containerRegister.value = it }
            }
        }
    }
}