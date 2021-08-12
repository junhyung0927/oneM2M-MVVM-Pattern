package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.data.AELocalDataSource
import com.example.onem2m_in_ae.data.AERemoteDataSource
import com.example.onem2m_in_ae.data.LocalDataSource
import com.example.onem2m_in_ae.data.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> {
        AERemoteDataSource(inAEDataService = get())
    }
    single<LocalDataSource> {
        AELocalDataSource(inAEDao = get())
    }
}