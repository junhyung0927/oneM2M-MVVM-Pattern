package com.example.onem2m_in_ae.model.response

import android.view.inspector.IntFlagMapping

data class ResponseM2MCon(
    val parentId: String,
    val resourceId: String,
    val type: Int,
    val creationTime: String,
    val stateTag: Int,
    val resourceName: String,
    val lastModifiedTime: String,
    val expirationTime: String,
    val labels: List<String>,
    val cr: String,
    val mni: Int,
    val mbs: Int,
    val mia: Int,
    val cni: Int,
    val cbs: Int
)