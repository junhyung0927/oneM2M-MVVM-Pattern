package com.example.onem2m_in_ae.ui.view

import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.ui.base.BaseViewModel
import com.example.onem2m_in_ae.repository.INAERepository
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.liveData

class INAEViewModel(private val inAERepository: INAERepository) : BaseViewModel() {
     fun getAEInfo() = liveData<ResponseAE>(Dispatchers.IO) {
         emit(inAERepository.getAEInfoList())
     }
}