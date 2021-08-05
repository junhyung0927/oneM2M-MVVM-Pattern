package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.request.RequestCnt
import com.example.onem2m_in_ae.model.request.RequestCon
import com.example.onem2m_in_ae.model.request.RequestSub
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon
import com.example.onem2m_in_ae.service.INAEDataService

class AERemoteDataSource(
    val inAEDataService: INAEDataService
) : RemoteDataSource {
    override suspend fun createAE(param: RequestAE): Unit {
        return inAEDataService.createAE(param)
    }

    override suspend fun createContainer(param: RequestCon) {
        return inAEDataService.createContainer(param)
    }

    override suspend fun createSubscription(requestSub: RequestSub, resourceName: String) {
//        return inAEDataService.createSubscription(resourceName, requestSub)
        return inAEDataService.createSubscription111(requestSub)
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

    override suspend fun deviceControl(contentInstance: RequestCnt, resourceName: String) {
        return inAEDataService.deviceControl(resourceName, contentInstance)
    }

    override suspend fun deleteContainer(resourceName: String) {
        return inAEDataService.deleteContainer(resourceName)
    }
}