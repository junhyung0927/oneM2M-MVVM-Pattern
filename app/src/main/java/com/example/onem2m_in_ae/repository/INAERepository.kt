package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.model.ResponseAE

interface INAERepository {
    suspend fun getAEInfoList(): ResponseAE
}