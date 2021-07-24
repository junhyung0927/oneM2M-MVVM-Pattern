package com.example.onem2m_in_ae.model.request


import com.google.gson.annotations.SerializedName

data class RequestAE(
    @SerializedName("m2m:ae")
    val m2mAe: RequestM2mAE
)