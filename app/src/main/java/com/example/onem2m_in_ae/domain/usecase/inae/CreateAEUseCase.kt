package com.example.onem2m_in_ae.domain.usecase.inae

import com.example.onem2m_in_ae.domain.usecase.NonParamCoroutineUseCase
import com.example.onem2m_in_ae.repository.OneM2MRepository
import kotlinx.coroutines.CoroutineDispatcher

class CreateAEUseCase(
    private val oneM2MRepository: OneM2MRepository,
    ioDispatcher: CoroutineDispatcher
) :  NonParamCoroutineUseCase<Unit>(ioDispatcher) {

    override suspend fun execute() {
        return oneM2MRepository.createAE()
    }
}