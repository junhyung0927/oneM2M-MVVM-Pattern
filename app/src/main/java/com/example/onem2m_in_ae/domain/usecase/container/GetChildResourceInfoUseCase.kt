package com.example.onem2m_in_ae.domain.usecase.container

import com.example.onem2m_in_ae.domain.usecase.NonParamCoroutineUseCase
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_in_ae.repository.OneM2MRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetChildResourceInfoUseCase(
    private val oneM2MRepository: OneM2MRepository,
    ioDispatcher: CoroutineDispatcher
) : NonParamCoroutineUseCase<ResponseCntUril>(ioDispatcher) {
    override suspend fun execute(): ResponseCntUril {
        return oneM2MRepository.getChildResourceInfo()
    }
}