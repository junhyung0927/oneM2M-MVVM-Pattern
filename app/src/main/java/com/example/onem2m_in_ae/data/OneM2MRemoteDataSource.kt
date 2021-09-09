package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.request.RequestCin
import com.example.onem2m_in_ae.model.request.RequestSub
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import com.example.onem2m_in_ae.service.OneM2MService

class OneM2MRemoteDataSource(
    val oneM2MService: OneM2MService
) : RemoteDataSource {
    override suspend fun createAE(param: RequestAE): Unit {
        return oneM2MService.createAE(param)
    }

    override suspend fun createSubscription(requestSub: RequestSub, resourceName: String) {
        return oneM2MService.createSubscription(resourceName, requestSub)
    }

    override suspend fun getAEInfo(): ResponseAE {
        return oneM2MService.getAEInfo()
    }

    override suspend fun getContentInstanceInfo(resourceName: String): ResponseCin {
        return oneM2MService.getDetailedChildResourceContentInstanceInfo(resourceName)
    }

    override suspend fun getChildResourceInfo(): ResponseCntUril {
        return oneM2MService.getChildResourceInfo()
    }

    override suspend fun getContainerInfo(): ResponseCnt {
        return oneM2MService.getContainerInfo()
    }

    override suspend fun deviceControl(contentInstance: RequestCin, resourceName: String) {
        return oneM2MService.deviceControl(resourceName, contentInstance)
    }
}