package com.example.onem2m_in_ae.model.response


import com.google.gson.annotations.SerializedName

data class ResponseAE(
    @SerializedName("m2m:ae")
    val m2m_ae: ResponseM2mAE
)