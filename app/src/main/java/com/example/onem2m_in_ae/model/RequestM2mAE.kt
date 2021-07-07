package com.example.onem2m_in_ae.model


import com.google.gson.annotations.SerializedName

data class RequestM2mAE(
    val api: String,
    val lbl: List<String>,
    val poa: List<String>,
    val rn: String,
    val rr: Boolean
)