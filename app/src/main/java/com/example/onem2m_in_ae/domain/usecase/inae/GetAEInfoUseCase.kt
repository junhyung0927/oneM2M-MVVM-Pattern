package com.example.onem2m_in_ae.domain.usecase.inae

import com.example.onem2m_in_ae.domain.usecase.NonParamCoroutineUseCase
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.repository.OneM2MRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetAEInfoUseCase(
    private val oneM2MRepository: OneM2MRepository,
    ioDispatcher: CoroutineDispatcher
) : NonParamCoroutineUseCase<ResponseAE>(ioDispatcher) {
    override suspend fun execute(): ResponseAE {
        return oneM2MRepository.getAEInfo()
    }
}