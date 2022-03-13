package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.domain.usecase.inae.CreateAEUseCase
import com.example.onem2m_in_ae.domain.usecase.inae.GetAEInfoUseCase
import com.example.onem2m_in_ae.domain.usecase.inae.GetContainerInstanceInfoListUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAEInfoUseCase(get(), get(named(IO))) }
    factory { CreateAEUseCase(get(), get(named(IO))) }
    factory { GetContainerInstanceInfoListUseCase(get(), get(named(IO))) }
}