package com.example.onem2m_in_ae.domain.usecase.container

import com.example.onem2m_in_ae.domain.usecase.CoroutineUseCase
import com.example.onem2m_in_ae.repository.OneM2MRepository
import kotlinx.coroutines.CoroutineDispatcher

class CreateSubscriptionUseCase(
    private val oneM2MRepository: OneM2MRepository,
    ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<String, Unit>(ioDispatcher) {
    override suspend fun execute(param: String) {
        return oneM2MRepository.createSubscription(param)
    }
}