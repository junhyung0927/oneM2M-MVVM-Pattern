package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon

interface INAERepository {
    fun createContainerInstance()
    suspend fun createAE() : Unit
    suspend fun getAEInfo() : ResponseAE
    suspend fun getContainerInfo() : ResponseCon
    suspend fun getContentInstanceInfo() : ResponseCnt
    suspend fun getContentInstanceDatabase() : MutableList<ContainerInstance>
    suspend fun insertContainerInstanceList(containerImageList: MutableList<ContainerInstance>)
}