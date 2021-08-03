package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.data.LocalDataSource
import com.example.onem2m_in_ae.data.RemoteDataSource
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_in_ae.model.request.*
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon

class INAERepositoryImpl(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) : INAERepository {

    override suspend fun createAE() {
        val requestAE = RequestAE(
            RequestM2mAE(
                "junhyung",
                "0.2.481.2.0001.001.000111",
                arrayListOf("key1", "key2"),
                true
            )
        )
        return remoteDataSource.createAE(requestAE)
    }

    override suspend fun createContainer(name: String) {
        val requestCon = RequestCon(
            RequestM2MCon(
                name,
                arrayListOf(name),
                3153600000
            )
        )
        return remoteDataSource.createContainer(requestCon)
    }

    override suspend fun getAEInfo(): ResponseAE {
        return remoteDataSource.getAEInfo()
    }

    override suspend fun getContentInstanceInfo(): ResponseCnt {
        return remoteDataSource.getContentInstanceInfo()
    }

    override suspend fun getContentInstanceDatabase(): List<ContainerInstance> {
        return localDataSource.getContainerInstanceDataBase()
    }

    override suspend fun getContainerInfo(): ResponseCon {
        return remoteDataSource.getContainerInfo()
    }

    override suspend fun registerContainerInstance(
        containerName: String,
        containerImage: Int,
        containerType: ContainerType
    ) {
        val containerInstance = listOf(
            ContainerInstance(
                containerInstanceName = containerName,
                containerImage = containerImage,
                type = containerType
            )
        )

        return localDataSource.registerContainerInstance(containerInstance)
    }

    override suspend fun deviceControl(content: String, resourceName: String) {
        val contentInstance = RequestCnt(
            RequestM2MCnt(
                content
            )
        )
        return remoteDataSource.deviceControl(contentInstance, resourceName)
    }

    override suspend fun deleteContainer(resourceName: String) {
        return remoteDataSource.deleteContainer(resourceName)
    }

    override suspend fun deleteDatabaseContainer(resourceName: String) {
        return localDataSource.deleteDatabaseContainer(resourceName)
    }
}