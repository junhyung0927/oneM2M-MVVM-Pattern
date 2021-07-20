package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.RequestAE
import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.model.ResponseCnt

interface INAEDataSource {
    suspend fun createAEINInfoList(param: RequestAE): Unit
    suspend fun getAE(): ResponseAE
    suspend fun getContentInstanceInfo(): ResponseCnt
}