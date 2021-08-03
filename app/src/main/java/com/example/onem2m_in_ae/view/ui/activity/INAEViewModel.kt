package com.example.onem2m_in_ae.view.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.view.base.BaseViewModel
import com.example.onem2m_in_ae.repository.INAERepository
import androidx.lifecycle.liveData
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.util.Event
import retrofit2.HttpException
import java.lang.Exception

class INAEViewModel(private val inAERepository: INAERepository) : BaseViewModel() {
    val createAE = liveData<Unit> {
        handle { inAERepository.createAE() }?.let {
            emit(it)
        }
    }

    val getContainerDatabase = liveData<List<ContainerInstance>> {
        handle { inAERepository.getContentInstanceDatabase() }?.let {
            emit(it)
        }
    }

    val getAEInfo = liveData<ResponseAE> {
        handle { inAERepository.getAEInfo() }?.let {
            emit(it)
        }
    }

    private val _onContainerItemEvent = MutableLiveData<Event<ContainerInstance>>()
    val onContainerItemEvent: LiveData<Event<ContainerInstance>>
        get() = _onContainerItemEvent

    fun callOnContainerImageEvent(containerItem: ContainerInstance) {
        _onContainerItemEvent.value = Event(containerItem)
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


