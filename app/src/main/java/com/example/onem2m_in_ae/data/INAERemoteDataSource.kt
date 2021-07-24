package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon
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

    override suspend fun getContentInstanceInfo(): ResponseCnt {
        return inAEDataService.getDetailedChildResourceContentInstanceInfo()
    }

    override suspend fun getContainerInfo(): ResponseCon {
        return inAEDataService.getContainerInfo()
    }
}