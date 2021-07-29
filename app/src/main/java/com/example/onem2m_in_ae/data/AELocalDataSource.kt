package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.room.INAEDao

class AELocalDataSource(
    private val inAEDao: INAEDao
) : LocalDataSource {
    override suspend fun insertContentInstanceInfoList(containerInstance: MutableList<ContainerInstance>)
            : Unit
    {
        return inAEDao.insertContainerInstanceList(*containerInstance.toTypedArray())
    }

    override suspend fun getContainerInstanceDataBase(): List<ContainerInstance> {
        return inAEDao.getContainerInstanceInfoList()
    }
}