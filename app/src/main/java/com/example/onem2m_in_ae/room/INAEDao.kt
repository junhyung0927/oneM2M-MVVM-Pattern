package com.example.onem2m_in_ae.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onem2m_in_ae.model.ContainerInstance
import org.jetbrains.annotations.NotNull

@Dao
interface INAEDao {
    @Query("SELECT * FROM ContainerInstance")
    fun getContainerInstanceInfoList(): MutableList<ContainerInstance>

    @Insert
    suspend fun insertContainerInstanceList(vararg containerInstance: ContainerInstance)
            : Unit

    @Insert
    fun createContainerInstance(containerInstance: MutableList<ContainerInstance>)
            : Unit
}
