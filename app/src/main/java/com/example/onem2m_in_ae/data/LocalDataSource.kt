package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.ContainerInstance

interface LocalDataSource {
    suspend fun insertContentInstanceInfoList(containerInstance: MutableList<ContainerInstance>)
            : Unit
    fun createContentInstance(containerInstance: MutableList<ContainerInstance>)
            : Unit
    suspend fun getContainerInstanceDataBase(): MutableList<ContainerInstance>

}