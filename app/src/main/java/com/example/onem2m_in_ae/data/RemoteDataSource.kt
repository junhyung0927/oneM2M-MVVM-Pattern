package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.request.RequestCin
import com.example.onem2m_in_ae.model.request.RequestCnt
import com.example.onem2m_in_ae.model.request.RequestSub
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.model.response.ResponseCnt

interface RemoteDataSource {
    suspend fun createAE(param: RequestAE)
    suspend fun createContainer(param: RequestCnt)
    suspend fun createSubscription(requestSub: RequestSub, resourceName: String)

    suspend fun getAEInfo(): ResponseAE
    suspend fun getContainerInfo(): ResponseCnt
    suspend fun getContentInstanceInfo(resourceName: String): ResponseCin
    suspend fun deviceControl(contentInstance: RequestCin, resourceName: String)
    suspend fun deleteContainer(resourceName: String)
}