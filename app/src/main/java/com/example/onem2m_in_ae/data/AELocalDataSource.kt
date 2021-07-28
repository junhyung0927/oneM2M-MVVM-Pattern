package com.example.onem2m_in_ae.data

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.room.INAEDao

class AELocalDataSource(
    private val inAEDao: INAEDao
) : LocalDataSource {
//    override suspend fun getContentInstanceInfoList(containerInstance: MutableList<ContainerInstance>): MutableList<ContainerInstance> {
//        TODO("Not yet implemented")
//    }
}