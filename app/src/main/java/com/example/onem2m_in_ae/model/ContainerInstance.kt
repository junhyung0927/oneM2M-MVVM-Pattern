package com.example.onem2m_in_ae.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "container")
data class ContainerInstance(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    val deviceName: String,
    val deviceImage: Int = 0,
    val deviceType: DeviceType
) : Serializable



