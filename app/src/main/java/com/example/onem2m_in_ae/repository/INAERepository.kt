package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.model.RequestAE
import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.model.ResponseCnt
import com.example.onem2m_in_ae.model.ResponseM2mAE

interface INAERepository {
    suspend fun createAE() : Unit
    suspend fun getAEInfo() : ResponseAE
    suspend fun getContentInstanceInfo() : ResponseCnt
}