package com.example.onem2m_in_ae.repository

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon

interface INAERepository {
    fun createContainerInstance()
    suspend fun createAE()
    suspend fun createContainer(name: String)
    suspend fun getAEInfo() : ResponseAE
    suspend fun getContainerInfo() : ResponseCon
    suspend fun getContentInstanceInfo() : ResponseCnt
    suspend fun getContentInstanceDatabase() : List<ContainerInstance>
    suspend fun insertContainerInstanceList(containerImageList: List<ContainerInstance>)
    suspend fun registerContainerInstance(containerName: String, containerImage: Int)
    suspend fun deviceControl(content: String, resourceName: String)
}