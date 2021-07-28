package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.repository.INAERepository
import com.example.onem2m_in_ae.repository.INAERepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<INAERepository> {
        INAERepositoryImpl(
            remoteDataSource = get(),
            localDataSource =  get()
        )
    }
}