package com.example.onem2m_in_ae.service

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.request.RequestCnt
import com.example.onem2m_in_ae.model.request.RequestCon
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCnt
import com.example.onem2m_in_ae.model.response.ResponseCon
import retrofit2.http.*

interface INAEDataService {
    @Headers(
        "Content-Type: application/json;ty=2",
    )
    @POST("/Mobius")
    suspend fun createAE(
        @Body param: RequestAE
    )

    @Headers(
        "Content-Type: application/json;ty=3",
    )
    @POST("/Mobius/junhyung_4")
    suspend fun createContainer(
        @Body param: RequestCon
    )

    @Headers(
        "Content-Type: application/json;ty=4",
    )
    @POST("/Mobius/junhyung_4/aircon")
    suspend fun deviceControl(
        @Body param: RequestCnt
    )

    @GET("/Mobius/junhyung_4")
    suspend fun getAEInfo(): ResponseAE

    @GET("/Mobius/IYAHN_DEMO/co2/la")
    suspend fun getContentInstanceLastResource(): ResponseCnt

    @GET("/Mobius/IYAHN_DEMO/co2?fu=1&rcn=1")
    suspend fun getChildResourceContentInstanceInfo(): ResponseCnt

    @GET("/Mobius/IYAHN_DEMO/co2?fu=2&rcn=4")
    suspend fun getDetailedChildResourceContentInstanceInfo(): ResponseCnt

    @GET("/Mobius/IYAHN_DEMO/co2?fu=2&rcn=1")
    suspend fun getOwnResourceContentInstanceInfo(): ResponseCnt

    @GET("/Mobius/IYAHN_DEMO/co2")
    suspend fun getContentInstanceResourceInfo(): ResponseCnt

    @GET("/Mobius?fu=1&rcn=1")
    suspend fun getContainerInfo(): ResponseCon
}