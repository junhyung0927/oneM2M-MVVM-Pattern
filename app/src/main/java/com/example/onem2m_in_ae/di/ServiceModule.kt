package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.service.INAEDataService
import org.koin.dsl.module

val serviceModule = module {
    single<INAEDataService> {

    }
}