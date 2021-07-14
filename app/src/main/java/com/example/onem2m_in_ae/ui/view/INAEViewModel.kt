package com.example.onem2m_in_ae.ui.view

import androidx.lifecycle.LiveData
import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.ui.base.BaseViewModel
import com.example.onem2m_in_ae.repository.INAERepository
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class INAEViewModel(private val inAERepository: INAERepository) : BaseViewModel() {
     val getAEInfo = liveData<ResponseAE> {
         emit(inAERepository.getAEInfoList())
     }

    fun getAE(){
        viewModelScope.launch {
            inAERepository.getAEInfoList()
        }
    }
}


