package com.example.onem2m_in_ae.model


import com.google.gson.annotations.SerializedName

data class RequestM2mAE(
    val rn: String,
    val api: String,
    val lbl: ArrayList<String>,
    val rr: Boolean
)