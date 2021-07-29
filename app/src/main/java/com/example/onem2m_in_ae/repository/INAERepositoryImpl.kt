package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.data.LocalDataSource
import com.example.onem2m_in_ae.data.RemoteDataSource
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.request.RequestM2mAE
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon

class INAERepositoryImpl(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) : INAERepository {
    override suspend fun createAE(): Unit {
        val requestAE = RequestAE(
            RequestM2mAE(
                "junhyung_4",
                "0.2.481.2.0001.001.000111",
                arrayListOf("key1", "key2"),
                true
            )
        )
        return remoteDataSource.createAE(requestAE)
    }

    override fun createContainerInstance(): Unit {
        val containerInstance = mutableListOf(
            ContainerInstance(1, "에어컨", R.drawable.airconditioner),
            ContainerInstance(2, "제습기", R.drawable.airpurifier),
            ContainerInstance(3, "보일러", R.drawable.boiler)
        )
        println("reposi" + containerInstance)
        localDataSource.createContentInstance(containerInstance)
    }

    override suspend fun getAEInfo(): ResponseAE {
        return remoteDataSource.getAEInfo()
    }

    override suspend fun getContentInstanceInfo(): ResponseCnt {
        return remoteDataSource.getContentInstanceInfo()
    }

    override suspend fun getContentInstanceDatabase(): MutableList<ContainerInstance> {
        return localDataSource.getContainerInstanceDataBase()
    }

    override suspend fun getContainerInfo(): ResponseCon {
        return remoteDataSource.getContainerInfo()
    }

    override suspend fun insertContainerInstanceList(containerImageList: MutableList<ContainerInstance>) {
        return localDataSource.insertContentInstanceInfoList(containerImageList)
    }
}