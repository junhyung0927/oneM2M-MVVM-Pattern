package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.data.LocalDataSource
import com.example.onem2m_in_ae.data.RemoteDataSource
import com.example.onem2m_in_ae.model.ContainerInstance
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

    override fun createContainerInstance() {

        val containerInstance = mutableListOf(
            ContainerInstance(
                1,
                containerInstanceName = "에어컨",
                containerImage = R.drawable.airconditioner
            ),
            ContainerInstance(
                2,
                containerInstanceName = "제습기",
                containerImage = R.drawable.airpurifier
            ),
            ContainerInstance(3, containerInstanceName = "보일러", containerImage = R.drawable.boiler)
        )


        localDataSource.createContentInstance(containerInstance)
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

    override suspend fun insertContainerInstanceList(containerImageList: List<ContainerInstance>) {
        return localDataSource.insertContentInstanceInfoList(containerImageList)
    }

    override suspend fun registerContainerInstance(
        containerName: String,
        containerImage: Int
    ) {

        val containerInstance = listOf(
            ContainerInstance(containerInstanceName = containerName, containerImage = containerImage)
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
}