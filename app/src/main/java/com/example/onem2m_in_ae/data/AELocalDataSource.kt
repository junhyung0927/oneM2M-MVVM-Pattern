package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.room.INAEDao

class AELocalDataSource(
    private val inAEDao: INAEDao
) : LocalDataSource
{
    override fun createContentInstance(containerInstance: MutableList<ContainerInstance>)
    {
        inAEDao.createContainerInstance(containerInstance)
    }

    override suspend fun insertContentInstanceInfoList(containerInstance: MutableList<ContainerInstance>)
    {
        return inAEDao.insertContainerInstanceList(*containerInstance.toTypedArray())
    }


    override suspend fun getContainerInstanceDataBase(): MutableList<ContainerInstance>
    {
        return inAEDao.getContainerInstanceInfoList()
    }
}