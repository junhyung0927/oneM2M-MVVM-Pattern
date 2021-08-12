package com.example.onem2m_in_ae.service

import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.request.RequestCin
import com.example.onem2m_in_ae.model.request.RequestCnt
import com.example.onem2m_in_ae.model.request.RequestSub
import com.example.onem2m_in_ae.model.response.ResponseAE
import com.example.onem2m_in_ae.model.response.ResponseCin
import com.example.onem2m_in_ae.model.response.ResponseCnt
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
    @POST("/Mobius/junhyung")
    suspend fun createContainer(
        @Body param: RequestCnt
    )

    @Headers(
        "Content-Type: application/json;ty=4",
    )
    @POST("/Mobius/junhyung/{resource_name}")
    suspend fun deviceControl(
        @Path("resource_name", encoded = true) resourceName: String,
        @Body param: RequestCin
    )

    @Headers(
        "Content-Type: application/json;ty=23",
    )
    @POST("/Mobius/junhyung/{resource_name}")
    suspend fun createSubscription(
        @Path("resource_name", encoded = true) resourceName: String,
        @Body param: RequestSub
    )

    @Headers(
        "Content-Type: application/json;ty=23",
    )
    @POST("/Mobius/junhyung/co2")
    suspend fun createSubscription111(
        @Body param: RequestSub
    )

    @GET("/Mobius/junhyung?fu=2&rcn=1")
    suspend fun getAEInfo(): ResponseAE

    @GET("/Mobius/junhyung/co2/la")
    suspend fun getContentInstanceLastResource(): ResponseCin

    @GET("/Mobius/junhyung/co2?fu=1&rcn=1")
    suspend fun getChildResourceContentInstanceInfo(): ResponseCin

    @GET("/Mobius/junhyung/{resource_name}?fu=2&rcn=4")
    suspend fun getDetailedChildResourceContentInstanceInfo(
        @Path("resource_name", encoded = true) resourceName: String
    ): ResponseCin

    @GET("/Mobius/junhyung/co2?fu=2&rcn=1")
    suspend fun getOwnResourceContentInstanceInfo(): ResponseCin

    @GET("/Mobius/junhyung/co2")
    suspend fun getContentInstanceResourceInfo(): ResponseCin

    @GET("/Mobius/junhyung?fu=2&rcn=1")
    suspend fun getContainerInfo(): ResponseCnt

    @DELETE("/Mobius/junhyung/{resource_name}")
    suspend fun deleteContainer(
        @Path("resource_name", encoded = true) resourceName: String
    )
}