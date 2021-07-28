package com.example.onem2m_in_ae.room

import androidx.room.Dao
import androidx.room.Query
import com.example.onem2m_in_ae.model.ContainerInstance

@Dao
interface INAEDao {
    @Query("SELECT * FROM ContainerInstance")
    fun getContainerInstanceInfoList(): MutableList<ContainerInstance>
}