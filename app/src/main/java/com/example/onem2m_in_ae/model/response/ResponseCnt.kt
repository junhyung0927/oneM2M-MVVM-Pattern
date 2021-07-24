package com.example.onem2m_in_ae.model.response

import com.google.gson.annotations.SerializedName

data class ResponseCnt(
    @SerializedName("m2m:cnt")
    val m2MCnt: ResponseM2MCnt
)