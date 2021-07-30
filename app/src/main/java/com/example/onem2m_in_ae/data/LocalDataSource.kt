package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.ContainerInstance

interface LocalDataSource {
    suspend fun insertContentInstanceInfoList(containerInstance: List<ContainerInstance>)
    fun createContentInstance(containerInstance: List<ContainerInstance>)
    suspend fun getContainerInstanceDataBase(): List<ContainerInstance>
    suspend fun registerContainerInstance(containerInstance: List<ContainerInstance>)
}