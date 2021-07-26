package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.MutableLiveData
import com.example.onem2m_in_ae.repository.INAERepository
import com.example.onem2m_in_ae.view.base.BaseViewModel

class ContainerRegisterViewModel(private val inAERepository: INAERepository): BaseViewModel() {
    val containerNameText = MutableLiveData<String>()

    init {
        containerNameText.value = ""
    }
}