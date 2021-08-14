package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.request.RequestCin
import com.example.onem2m_in_ae.model.request.RequestCnt
import com.example.onem2m_in_ae.model.request.RequestSub
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_in_ae.service.INAEDataService

class AERemoteDataSource(
    val inAEDataService: INAEDataService
) : RemoteDataSource {
    override suspend fun createAE(param: RequestAE): Unit {
        return inAEDataService.createAE(param)
    }

    override suspend fun createSubscription(requestSub: RequestSub, resourceName: String) {
        return inAEDataService.createSubscription(resourceName, requestSub)
    }

    override suspend fun getAEInfo(): ResponseAE {
        return inAEDataService.getAEInfo()
    }

    override suspend fun getContentInstanceInfo(resourceName: String): ResponseCin {
        return inAEDataService.getDetailedChildResourceContentInstanceInfo(resourceName)
    }

    override suspend fun getChildResourceInfo(): ResponseCntUril {
        return inAEDataService.getChildResourceInfo()
    }

    override suspend fun getContainerInfo(): ResponseCnt {
        return inAEDataService.getContainerInfo()
    }

    override suspend fun deviceControl(contentInstance: RequestCin, resourceName: String) {
        return inAEDataService.deviceControl(resourceName, contentInstance)
    }
}