package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.request.RequestCnt
import com.example.onem2m_in_ae.model.request.RequestCon
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon

interface RemoteDataSource {
    suspend fun createAE(param: RequestAE)
    suspend fun createContainer(param: RequestCon)
    suspend fun getAEInfo(): ResponseAE
    suspend fun getContainerInfo(): ResponseCon
    suspend fun getContentInstanceInfo(): ResponseCnt
    suspend fun deviceControl(contentInstance: RequestCnt, resourceName: String)
    suspend fun deleteContainer(resourceName: String)
}