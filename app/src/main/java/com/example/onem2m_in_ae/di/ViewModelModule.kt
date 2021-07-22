package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.view.ui.activity.INAEViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { INAEViewModel(inAERepository = get()) }
}