package com.example.onem2m_in_ae.ui.view

import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.ui.base.BaseViewModel
import com.example.onem2m_in_ae.repository.INAERepository
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.onem2m_in_ae.model.RequestAE
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import retrofit2.HttpException
import java.lang.Exception

class INAEViewModel(private val inAERepository: INAERepository) : BaseViewModel() {
    fun createAEInfo() = liveData<Unit> {
        handle { inAERepository.createAEInfoList() }?.let {
            emit(it)
        }
    }

    fun getAE() = liveData<ResponseAE> {
        handle { inAERepository.getAE() }?.let {
            emit(it)
        }
    }

    override fun onError(e: Exception) {
        super.onError(e)
        when(e) {
            is HttpException -> {
                when(e.code()) {
                    400 -> println("400: 잘못된 요청입니다.")
                    403 -> println("403: 접근 허용 거부입니다.")
                    409 -> println("409: 이미 생성된 리소스가 있습니다.")
                    500 -> println("500: 서버 에러입니다.")
                }
            }
            else -> e.printStackTrace()
        }
    }
}


