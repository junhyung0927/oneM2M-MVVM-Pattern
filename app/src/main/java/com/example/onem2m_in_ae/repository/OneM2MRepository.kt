package com.example.onem2m_in_ae.repository

import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.DeviceType
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCntUril

interface OneM2MRepository {
    //AE
    //Container

    //등록 및 생성
    suspend fun createAE()
    suspend fun registerContainerInstance(containerInstance: ContainerInstance)
    suspend fun createSubscription(resourceName: String)

    //조회
    suspend fun getAEInfo() : ResponseAE

    suspend fun getContainerInfo() : ResponseCnt

    suspend fun getContentInstanceInfo(resourceName: String) : ResponseCin

    suspend fun getContainerInstanceInfoList() : List<ContainerInstance>

    suspend fun getChildResourceInfo() : ResponseCntUril

    //수정
    suspend fun deviceControl(content: String, resourceName: String)

    //삭제
    suspend fun deleteDatabaseContainer(resourceName: String)
}