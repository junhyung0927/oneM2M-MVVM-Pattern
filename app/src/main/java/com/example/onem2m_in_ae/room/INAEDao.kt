package com.example.onem2m_in_ae.room

import androidx.room.*
import com.example.onem2m_in_ae.model.ContainerInstance
import org.jetbrains.annotations.NotNull

@Dao
interface INAEDao {
    @Query("SELECT * FROM container")
    fun getContainerInstanceInfoList(): List<ContainerInstance>

    @Insert
    suspend fun insertContainerInstanceList(vararg containerInstance: ContainerInstance)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createContainerInstance(containerInstance: List<ContainerInstance>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerContainerInstance(containerInstance: List<ContainerInstance>)

    @Query("DELETE FROM container")
    fun deleteAll()

    @Query("DELETE FROM container WHERE containerInstanceName = :resourceName ")
    fun deleteContainer(resourceName: String)
}
