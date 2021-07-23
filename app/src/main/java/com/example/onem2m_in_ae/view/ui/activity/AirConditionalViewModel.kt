package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.liveData
import com.example.onem2m_in_ae.model.ResponseCnt
import com.example.onem2m_in_ae.repository.INAERepository
import com.example.onem2m_in_ae.view.base.BaseViewModel
import retrofit2.HttpException
import java.lang.Exception

class AirConditionalViewModel(private val inAERepository: INAERepository): BaseViewModel() {
    val getContentInstanceInfo = liveData<ResponseCnt> {
        handle { inAERepository.getContentInstanceInfo() }?.let {
            emit(it)
        }
    }

    override fun onError(e: Exception) {
        super.onError(e)
        when (e) {
            is HttpException -> {
                when (e.code()) {
                    400 -> println("400: 잘못된 요청입니다.")
                    403 -> println("403: 접근 허용 거부입니다.")
                    404 -> println("404: 해당 url은 존재하지 않습니다.")
                    409 -> println("409: 이미 생성된 리소스가 있습니다.")
                    500 -> println("500: 서버 에러입니다.")
                }
            }
            else -> e.printStackTrace()
        }
    }
}