package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon

interface INAERepository {
    suspend fun createAE() : Unit
//    suspend fun createContainerInstance() : Unit
    suspend fun getAEInfo() : ResponseAE
    suspend fun getContainerInfo() : ResponseCon
    suspend fun getContentInstanceInfo() : ResponseCnt
}