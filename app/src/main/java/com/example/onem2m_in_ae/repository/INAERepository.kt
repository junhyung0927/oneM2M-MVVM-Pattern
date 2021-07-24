package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.model.ResponseCon

interface INAERepository {
    suspend fun createAE() : Unit
    suspend fun getAEInfo() : ResponseAE
    suspend fun getContentInstanceInfo() : ResponseCon
}