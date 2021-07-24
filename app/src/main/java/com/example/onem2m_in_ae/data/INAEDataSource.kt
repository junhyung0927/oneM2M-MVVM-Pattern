package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.RequestAE
import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.model.ResponseCon

interface INAEDataSource {
    suspend fun createAE(param: RequestAE): Unit
    suspend fun getAEInfo(): ResponseAE
    suspend fun getContentInstanceInfo(): ResponseCon
}