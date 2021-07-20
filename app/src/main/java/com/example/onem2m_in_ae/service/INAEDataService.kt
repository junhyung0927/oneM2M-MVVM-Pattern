package com.example.onem2m_in_ae.service

import com.example.onem2m_in_ae.model.RequestAE
import com.example.onem2m_in_ae.model.ResponseAE
import com.example.onem2m_in_ae.model.ResponseCnt
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface INAEDataService {
    @Headers(
        "Accept: application/json",
        "X-M2M-RI: 123aaghjhk45",
        "X-M2M-Origin: S",
        "Content-Type: application/json;ty=2",
    )
    @POST("/Mobius")
    suspend fun createAEInfoList(
        @Body param: RequestAE
    ): Unit

    @Headers(
        "Accept: application/json",
        "X-M2M-RI: 1234asdfadd5",
        "X-M2M-Origin: S20170713200332320Bdfl",
    )
    @GET("/Mobius/junhyung_4")
    suspend fun getAE(): ResponseAE

    @Headers(
        "Accept: application/json",
        "X-M2M-RI: 12345",
        "X-M2M-Origin: Swisoft",
    )
    @GET("/Mobius/IYAHN_DEMO/co2/la")
    suspend fun getContentInstanceInfo(): ResponseCnt
}