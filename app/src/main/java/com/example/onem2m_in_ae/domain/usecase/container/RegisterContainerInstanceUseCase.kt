package com.example.onem2m_in_ae.domain.usecase.container

import com.example.onem2m_in_ae.domain.usecase.CoroutineUseCase
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.repository.OneM2MRepository
import kotlinx.coroutines.CoroutineDispatcher

class RegisterContainerInstanceUseCase(
    private val oneM2MRepository: OneM2MRepository,
    ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<ContainerInstance, Unit>(ioDispatcher) {
    override suspend fun execute(param: ContainerInstance) {
        oneM2MRepository.registerContainerInstance(param)
    }
}