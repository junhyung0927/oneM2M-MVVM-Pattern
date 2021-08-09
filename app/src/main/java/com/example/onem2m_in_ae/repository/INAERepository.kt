package com.example.onem2m_in_ae.repository

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon

interface INAERepository {
    //등록 및 생성
    suspend fun createAE()
    suspend fun createContainer(name: String)
    suspend fun registerContainerInstance(containerName: String, containerImage: Int, containerType: ContainerType)
    suspend fun createSubscription(resourceName: String)

    //조회
    suspend fun getAEInfo() : ResponseAE
    suspend fun getContainerInfo() : ResponseCon
    suspend fun getContentInstanceInfo(resourceName: String) : ResponseCnt
    suspend fun getContentInstanceDatabase() : List<ContainerInstance>

    //수정
    suspend fun deviceControl(content: String, resourceName: String)

    //삭제
    suspend fun deleteContainer(resourceName: String)
    suspend fun deleteDatabaseContainer(resourceName: String)
}