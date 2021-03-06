package com.example.onem2m_in_ae.room

import androidx.room.*
import com.example.onem2m_in_ae.model.ContainerInstance
import org.jetbrains.annotations.NotNull

@Dao
interface OneM2MDao {
    @Query("SELECT * FROM container")
    fun getContainerInstanceInfoList(): List<ContainerInstance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createContainerInstance(containerInstance: List<ContainerInstance>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerContainerInstance(containerInstance: ContainerInstance)

    @Query("DELETE FROM container")
    fun deleteAll()

    @Query("DELETE FROM container WHERE deviceName = :resourceName ")
    fun deleteContainer(resourceName: String)
}
