package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.ResponseAE

interface INAEDataSource {
    suspend fun getAEINInfoList(param: HashMap<String, Any?>) : ResponseAE
}