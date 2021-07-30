package com.example.onem2m_in_ae.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "container")
data class ContainerInstance(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    val containerInstanceName: String?,
    val containerImage: Int = 0
)



