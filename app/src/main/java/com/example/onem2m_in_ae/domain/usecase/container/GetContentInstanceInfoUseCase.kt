package com.example.onem2m_in_ae.domain.usecase.container

import com.example.onem2m_in_ae.domain.usecase.CoroutineUseCase
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.repository.OneM2MRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetContentInstanceInfoUseCase(
    private val oneM2MRepository: OneM2MRepository,
    ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<String, ResponseCin>(ioDispatcher) {
    override suspend fun execute(param: String): ResponseCin {
        return oneM2MRepository.getContentInstanceInfo(param)
    }

}