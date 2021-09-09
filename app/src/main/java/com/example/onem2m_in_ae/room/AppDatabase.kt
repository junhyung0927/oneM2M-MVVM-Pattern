package com.example.onem2m_in_ae.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onem2m_in_ae.model.ContainerInstance

@Database(entities = [ContainerInstance::class], version = 11)
abstract class AppDatabase : RoomDatabase(){
    companion object {
        const val DATABASE_NAME = "AE-database"
    }

    abstract fun oneM2MDao(): OneM2MDao
}