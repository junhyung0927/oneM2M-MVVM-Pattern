package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.repository.OneM2MRepository
import com.example.onem2m_in_ae.repository.OneM2MRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<OneM2MRepository> {
        OneM2MRepositoryImpl(
            remoteDataSource = get(),
            localDataSource =  get()
        )
    }
}