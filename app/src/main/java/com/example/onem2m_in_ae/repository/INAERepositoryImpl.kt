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
                "0.2.481.2.0001.001.000111",
                listOf("key1", "key2"),
                listOf("http://203.254.173.104:9727"),
                "justin",
                true)
        )

        return inAEDataSource.getAEINInfoList(hashMapOf(
            "lbl" to requestAE.m2mAe.lbl,
            "api" to requestAE.m2mAe.api,
            "rr" to requestAE.m2mAe.rr,
            "poa" to requestAE.m2mAe.poa,
            "rn" to requestAE.m2mAe.rn
        ))
    }
}