package com.example.onem2m_in_ae.model


import com.google.gson.annotations.SerializedName

data class ResponseAE(
    @SerializedName("m2m:ae")
    val m2mAe: ResponseM2mAE
)