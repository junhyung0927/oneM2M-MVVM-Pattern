package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.data.INAEDataSource
import com.example.onem2m_in_ae.model.RequestAE
import com.example.onem2m_in_ae.model.RequestM2mAE
import com.example.onem2m_in_ae.model.ResponseAE

class INAERepositoryImpl(
    val inAEDataSource: INAEDataSource
) : INAERepository {
    override suspend fun getAEInfoList(): ResponseAE {
        val requestAE = RequestAE(
            RequestM2mAE(
                "junhyung",
                "0.2.481.2.0001.001.000111",
                arrayListOf("key1", "key2"),
                true)
        )

        return inAEDataSource.getAEINInfoList(hashMapOf(
            "rn" to requestAE.m2mAe.rn,
            "api" to requestAE.m2mAe.api,
            "lbl" to requestAE.m2mAe.lbl,
            "rr" to requestAE.m2mAe.rr,
        ))
    }

    override suspend fun getAE(): ResponseAE {
        return inAEDataSource.getAE()
    }

}