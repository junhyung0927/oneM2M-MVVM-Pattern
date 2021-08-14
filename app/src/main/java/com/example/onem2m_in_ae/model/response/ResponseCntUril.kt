package com.example.onem2m_in_ae.model.response

import com.google.gson.annotations.SerializedName

data class ResponseCntUril (
    @SerializedName("m2m:uril")
    val m2mUril: List<String>
)