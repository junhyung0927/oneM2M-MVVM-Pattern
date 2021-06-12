package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.data.INAEDataSource
import com.example.onem2m_in_ae.model.ResponseAE

class INAERepositoryImpl(
    val inAEDataSource: INAEDataSource
) : INAERepository {
    override suspend fun getAEInfoList(): ResponseAE {
        return inAEDataSource.getAEINInfoList()
    }
}