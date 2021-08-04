package com.example.onem2m_in_ae.model.request

import com.google.gson.annotations.SerializedName

data class RequestM2MSub(
    val resourceName: String,
    @SerializedName("enc")
    val encNet: RequestEncNet,
    val notificationUri: String
)