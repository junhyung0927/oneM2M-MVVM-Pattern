package com.example.onem2m_in_ae.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onem2m_in_ae.model.ContainerInstance

@Database(entities = [ContainerInstance::class], version = 10)
abstract class AppDatabase : RoomDatabase(){
    companion object {
        const val DATABASE_NAME = "AE-database"
    }

    abstract fun inAEDao(): INAEDao
}