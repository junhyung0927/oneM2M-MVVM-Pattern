package com.example.onem2m_in_ae.domain.usecase.inae

import com.example.onem2m_in_ae.domain.usecase.NonParamCoroutineUseCase
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.repository.OneM2MRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetContainerInstanceInfoListUseCase(
    private val oneM2MRepository: OneM2MRepository,
    ioDispatcher: CoroutineDispatcher
): NonParamCoroutineUseCase<List<ContainerInstance>>(ioDispatcher){
    override suspend fun execute(): List<ContainerInstance> {
        return oneM2MRepository.getContainerInstanceInfoList()
    }
}