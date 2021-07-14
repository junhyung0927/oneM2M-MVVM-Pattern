package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.model.ResponseM2mAE

interface INAERepository {
    suspend fun getAEInfoList(): ResponseAE
    suspend fun getAE() : ResponseAE
}