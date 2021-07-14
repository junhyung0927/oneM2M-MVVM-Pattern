package com.example.onem2m_in_ae.service

import com.example.onem2m_in_ae.model.ResponseAE
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface INAEDataService {
    //    @Headers(
//        "Content-Type: application/vnd.onem2m-res+xml;ty=2",
//        "Accept: application/xml",
//        "locale: ko",
//        "X-M2M-Origin: SwisoftPlatform",
//        "X-M2M-RI: 12345",
//        "X-M2M-NM: wisoftPlatform",
//        "Content-Length: 100"
//    )
//    @POST("/Mobius")
//    suspend fun getAEInfoList(
//        @Body param: HashMap<String, Any?>
//    ): ResponseAE
    @Headers(
        "Accept: application/json",
        "X-M2M-RI: 123aaghjhk45",
        "X-M2M-Origin: S",
        "Content-Type: application/json;ty=2"
    )
    @POST("/Mobius")
    suspend fun getAEInfoList(
        @Body param: HashMap<String, Any?>
    ): ResponseAE

    @Headers(
        "Accept: application/json",
        "X-M2M-RI: 1234asdfadd5",
        "X-M2M-Origin: S20170713200332320Bdfl",
    )
    @GET("/Mobius/ae_test")
    suspend fun getAE(): ResponseAE
}