package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon

interface INAEDataSource {
    suspend fun createAE(param: RequestAE): Unit
    suspend fun getAEInfo(): ResponseAE
    suspend fun getContainerInfo(): ResponseCon
    suspend fun getContentInstanceInfo(): ResponseCnt
}