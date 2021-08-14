package com.example.onem2m_in_ae.model.response

import com.google.gson.annotations.SerializedName

data class ResponseCin(
    @SerializedName("m2m:cnt")
    val m2MCin: ResponseM2MCin
)