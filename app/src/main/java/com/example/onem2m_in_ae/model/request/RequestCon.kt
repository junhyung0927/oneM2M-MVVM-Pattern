package com.example.onem2m_in_ae.model.request

import com.google.gson.annotations.SerializedName

data class RequestCon(
    @SerializedName("m2m:cnt")
    val m2mcon: RequestM2MCon
)