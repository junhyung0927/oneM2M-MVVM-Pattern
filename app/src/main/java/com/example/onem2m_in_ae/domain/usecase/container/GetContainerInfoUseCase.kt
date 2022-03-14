package com.example.onem2m_in_ae.domain.usecase.container

import com.example.onem2m_in_ae.domain.usecase.CoroutineUseCase
import com.example.onem2m_in_ae.domain.usecase.NonParamCoroutineUseCase
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.repository.OneM2MRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetContainerInfoUseCase(
    private val oneM2MRepository: OneM2MRepository,
    ioDispatcher: CoroutineDispatcher,
) : NonParamCoroutineUseCase<ResponseCnt>(ioDispatcher){
    override suspend fun execute(): ResponseCnt {
        return oneM2MRepository.getContainerInfo()
    }
}