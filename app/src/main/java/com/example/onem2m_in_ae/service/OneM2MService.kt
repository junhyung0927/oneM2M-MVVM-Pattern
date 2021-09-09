package com.example.onem2m_in_ae.service

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.request.RequestCin
import com.example.onem2m_in_ae.model.request.RequestCnt
import com.example.onem2m_in_ae.model.request.RequestSub
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCntUril
import retrofit2.http.*

interface OneM2MService {
    @Headers(
        "Content-Type: application/json;ty=2",
    )
    @POST("/Mobius")
    suspend fun createAE(
        @Body param: RequestAE
    )

    @Headers(
        "Content-Type: application/json;ty=4",
    )
    @POST("/Mobius/IYAHN_DEMO/{resource_name}")
    suspend fun deviceControl(
        @Path("resource_name", encoded = true) resourceName: String,
        @Body param: RequestCin
    )

    @Headers(
        "Content-Type: application/json;ty=23",
    )
    @POST("/Mobius/IYAHN_DEMO/{resource_name}")
    suspend fun createSubscription(
        @Path("resource_name") resourceName: String,
        @Body param: RequestSub
    )

//    @GET("/Mobius/IYAHN_DEMO?fu=2&rcn=1")
//    suspend fun getAEInfo(): ResponseAE

    @GET("/Mobius/IYAHN_DEMO?fu=2&rcn=1")
    suspend fun getAEInfo(): ResponseAE

    @GET("/Mobius/IYAHN_DEMO/co2/la")
    suspend fun getContentInstanceLastResource(): ResponseCin

    @GET("/Mobius?fu=1&rcn=1")
    suspend fun getChildResourceInfo(): ResponseCntUril

    @GET("/Mobius/IYAHN_DEMO/{resource_name}?fu=2&rcn=1")
    suspend fun getDetailedChildResourceContentInstanceInfo(
        @Path("resource_name", encoded = true) resourceName: String
    ): ResponseCin

    @GET("/Mobius/IYAHN_DEMO?fu=2&rcn=1")
    suspend fun getContainerInfo(): ResponseCnt
}