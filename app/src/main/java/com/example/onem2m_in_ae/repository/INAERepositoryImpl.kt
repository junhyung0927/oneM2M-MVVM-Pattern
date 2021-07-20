package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.data.INAEDataSource
import com.example.onem2m_in_ae.model.RequestAE
import com.example.onem2m_in_ae.model.RequestM2mAE
import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.model.ResponseCnt

class INAERepositoryImpl(
    val inAEDataSource: INAEDataSource
) : INAERepository {
    override suspend fun createAEInfoList(): Unit {
        val requestAE = RequestAE(
            RequestM2mAE(
                "junhyung_4",
                "0.2.481.2.0001.001.000111",
                arrayListOf("key1", "key2"),
                true)
        )
        return inAEDataSource.createAEINInfoList(requestAE)
    }

    override suspend fun getAE(): ResponseAE {
        return inAEDataSource.getAE()
    }

    override suspend fun getContentInstanceInfo(): ResponseCnt {
        return inAEDataSource.getContentInstanceInfo()
    }
}