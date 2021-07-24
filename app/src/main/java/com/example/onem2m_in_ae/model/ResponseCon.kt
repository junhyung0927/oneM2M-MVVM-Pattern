package com.example.onem2m_in_ae.model

import com.google.gson.annotations.SerializedName

data class ResponseCon(
    @SerializedName("m2m:cnt")
    val m2MCon: ResponseM2MCon
)