package com.example.onem2m_in_ae.model.response


import com.google.gson.annotations.SerializedName

data class ResponseM2mAE(
    val pi: String,
    val ri: String,
    val ty: Int,
    val ct: String,
    val rn: String,
    val lt: String,
    val et: String,
    val lbl: ArrayList<String>,
    val api: String,
    val aei: String,
    val rr: String
)