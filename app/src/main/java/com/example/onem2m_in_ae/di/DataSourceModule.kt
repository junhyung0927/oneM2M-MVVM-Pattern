package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.data.OneM2MLocalDataSource
import com.example.onem2m_in_ae.data.OneM2MRemoteDataSource
import com.example.onem2m_in_ae.data.LocalDataSource
import com.example.onem2m_in_ae.data.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> {
        OneM2MRemoteDataSource(oneM2MService = get())
    }
    single<LocalDataSource> {
        OneM2MLocalDataSource(oneM2MDao = get())
    }
}