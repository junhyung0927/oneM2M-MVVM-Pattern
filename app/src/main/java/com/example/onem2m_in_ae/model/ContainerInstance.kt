package com.example.onem2m_in_ae.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContainerInstance(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var containerInstanceName: String,
    val containerImage: Int
)