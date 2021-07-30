package com.example.onem2m_in_ae.room

import androidx.room.*
import com.example.onem2m_in_ae.model.ContainerInstance
import org.jetbrains.annotations.NotNull

@Dao
interface INAEDao {
    @Query("SELECT * FROM container")
    fun getContainerInstanceInfoList(): MutableList<ContainerInstance>

    @Insert
    suspend fun insertContainerInstanceList(vararg containerInstance: ContainerInstance)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createContainerInstance(containerInstance: MutableList<ContainerInstance>)

    @Insert
    fun registerContainerInstance(containerInstance: MutableList<ContainerInstance>)

    @Query("DELETE FROM container")
    fun deleteAll()
}
