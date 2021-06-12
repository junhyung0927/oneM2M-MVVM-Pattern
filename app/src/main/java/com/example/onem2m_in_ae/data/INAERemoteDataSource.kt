package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.RequestAE
import com.example.onem2m_in_ae.model.RequestM2mAE
import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.service.INAEDataService

class INAERemoteDataSource(
    val inAEDataService: INAEDataService
) : INAEDataSource {
    override suspend fun getAEINInfoList(): ResponseAE {
        val requestAE = RequestAE(
            RequestM2mAE(
            "0.2.481.2.0001.001.000111",
                listOf("key1", "key2"),
                listOf("http://203.254.173.104:9727"),
                "justin",
                true))
        val input: HashMap<String, Any?> = hashMapOf()
        input.put("api",requestAE.m2mAe.api)
        input.put("lbl",requestAE.m2mAe.lbl)
        input.put("rr",requestAE.m2mAe.rr)
        input.put("poa",requestAE.m2mAe.poa)
        input.put("rn",requestAE.m2mAe.rn)

        return inAEDataService.getAEInfoList(input)
    }
}