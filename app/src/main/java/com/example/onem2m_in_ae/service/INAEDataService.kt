package com.example.onem2m_in_ae.service

import androidx.room.Delete
import com.example.onem2m_in_ae.model.request.RequestAE
import com.example.onem2m_in_ae.model.request.RequestCnt
import com.example.onem2m_in_ae.model.request.RequestCon
import com.example.onem2m_in_ae.model.request.RequestSub
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
    @POST("/Mobius/junhyung")
    suspend fun createContainer(
        @Body param: RequestCon
    )

    @Headers(
        "Content-Type: application/json;ty=4",
    )
    @POST("/Mobius/junhyung/{resource_name}")
    suspend fun deviceControl(
        @Path("resource_name", encoded = true) resourceName: String,
        @Body param: RequestCnt
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
    @POST("/Mobius/IYAHN_DEMO/co2")
    suspend fun createSubscription111(
        @Body param: RequestSub
    )

    @GET("/Mobius/junhyung?fu=2&rcn=1")
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

    @GET("/Mobius/junhyung?fu=2&rcn=1")
    suspend fun getContainerInfo(): ResponseCon

    @DELETE("/Mobius/junhyung/{resource_name}")
    suspend fun deleteContainer(
        @Path("resource_name", encoded = true) resourceName: String
    )
}