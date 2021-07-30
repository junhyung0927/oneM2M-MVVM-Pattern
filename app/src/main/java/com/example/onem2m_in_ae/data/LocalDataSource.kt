package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.ContainerInstance

interface LocalDataSource {
    suspend fun insertContentInstanceInfoList(containerInstance: MutableList<ContainerInstance>)
    fun createContentInstance(containerInstance: MutableList<ContainerInstance>)
    suspend fun getContainerInstanceDataBase(): MutableList<ContainerInstance>
    suspend fun registerContainerInstance(containerInstance: MutableList<ContainerInstance>)
}