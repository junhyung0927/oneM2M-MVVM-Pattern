package com.example.onem2m_in_ae.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class ContainerInstance(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val containerInstanceName: String = "",
    val containerImage: Int = 0
)