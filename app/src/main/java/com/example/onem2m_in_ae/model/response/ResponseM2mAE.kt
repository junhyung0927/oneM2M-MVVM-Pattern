package com.example.onem2m_in_ae.model.response


import com.google.gson.annotations.SerializedName

data class ResponseM2mAE(
    val aei: String,
    val api: String,
    val ct: String,
    val et: String,
    val lbl: List<String>,
    val lt: String,
    val pi: String,
    val poa: List<String>,
    val ri: String,
    val rn: String,
    val rr: Boolean,
    val ty: Int
)