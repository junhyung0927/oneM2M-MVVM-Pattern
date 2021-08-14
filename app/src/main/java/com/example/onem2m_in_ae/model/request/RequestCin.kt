package com.example.onem2m_in_ae.model.request

import com.google.gson.annotations.SerializedName

data class RequestCin (
    @SerializedName("m2m:cin")
    val m2mAdn: RequestM2MCin
)