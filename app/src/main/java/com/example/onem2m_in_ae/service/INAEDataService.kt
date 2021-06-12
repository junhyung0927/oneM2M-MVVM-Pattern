package com.example.onem2m_in_ae.service

import com.example.onem2m_in_ae.model.ResponseAE
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface INAEDataService {
    @Headers(
        "Content-Type: application/vnd.onem2m-res+xml;ty=2",
        "Accept: application/xml",
        "locale: ko",
        "X-M2M-Origin: SwisoftPlatform",
        "X-M2M-RI: 12345",
        "X-M2M-NM: wisoftPlatform",
        "Content-Length: 100"
    )
    @POST("/Mobius")
    suspend fun getAEInfoList(
        @Body param: HashMap<String, Any?>
    ): ResponseAE
}