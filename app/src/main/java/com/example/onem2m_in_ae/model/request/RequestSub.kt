package com.example.onem2m_in_ae.model.request

import com.google.gson.annotations.SerializedName

data class RequestSub (
    @SerializedName("m2m:sub")
    val m2mSub: RequestM2MSub
)