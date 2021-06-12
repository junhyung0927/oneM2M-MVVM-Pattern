package com.example.onem2m_in_ae.service

import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.model.RequestAE

class INAEDataServiceImpl : INAEDataService {
    override suspend fun getAEInfoList(param: HashMap<String, Any?>): ResponseAE {
        return ResponseAE()
    }
}