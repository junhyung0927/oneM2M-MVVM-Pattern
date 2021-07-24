package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.RequestAE
import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.model.ResponseCon
import com.example.onem2m_in_ae.service.INAEDataService

class INAERemoteDataSource(
    val inAEDataService: INAEDataService
) : INAEDataSource {
    override suspend fun createAE(param: RequestAE): Unit {
        return inAEDataService.createAE(param)
    }

    override suspend fun getAEInfo(): ResponseAE {
        return inAEDataService.getAEInfo()
    }

    override suspend fun getContentInstanceInfo(): ResponseCon {
        return inAEDataService.getDetailedChildResourceContentInstanceInfo()
    }
}