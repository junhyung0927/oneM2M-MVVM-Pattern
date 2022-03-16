package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.view.ui.activity.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { INAEViewModel(
        createAEUseCase = get(),
        getAEInfoUseCase = get(),
        getContainerInstanceInfoListUseCase = get()
    ) }
    viewModel { ContainerViewModel(
        getContentInstanceInfoUseCase = get(),
        deviceControlUseCase = get(),
        deleteContainerUseCase = get(),
        getContainerInfoUseCase = get(),
        createSubscriptionUseCase = get(),
        getChildResourceInfoUseCase = get()
    ) }
    viewModel { ContainerRegisterViewModel(registerContainerInstanceUseCase = get()) }
}