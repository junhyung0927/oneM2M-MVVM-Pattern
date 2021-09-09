package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.view.ui.activity.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { INAEViewModel(oneM2MRepository = get()) }
    viewModel { AirConditionalViewModel(oneM2MRepository = get()) }
    viewModel { AirPurifierViewModel(oneM2MRepository = get()) }
    viewModel { BoilerViewModel(oneM2MRepository = get()) }
    viewModel { ContainerRegisterViewModel(oneM2MRepository = get()) }
}