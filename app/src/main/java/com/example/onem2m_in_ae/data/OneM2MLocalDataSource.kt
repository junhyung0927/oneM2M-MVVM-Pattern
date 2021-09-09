package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.room.OneM2MDao

class OneM2MLocalDataSource(
    private val oneM2MDao: OneM2MDao
) : LocalDataSource
{
    override fun createContentInstance(containerInstance: List<ContainerInstance>) {
        oneM2MDao.createContainerInstance(containerInstance)
    }

    override suspend fun getContainerInstanceDataBase(): List<ContainerInstance> {
        return oneM2MDao.getContainerInstanceInfoList()
    }

    override suspend fun registerContainerInstance(containerInstance: List<ContainerInstance>) {
        return oneM2MDao.registerContainerInstance(containerInstance)
    }

    override suspend fun deleteDatabaseContainer(resoureName: String) {
        return oneM2MDao.deleteContainer(resoureName)
    }
}