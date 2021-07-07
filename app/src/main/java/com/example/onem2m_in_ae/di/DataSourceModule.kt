package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.data.INAEDataSource
import com.example.onem2m_in_ae.data.INAERemoteDataSource
import com.example.onem2m_in_ae.repository.INAERepositoryImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<INAEDataSource> { INAERemoteDataSource( inAEDataService = get() )}
}