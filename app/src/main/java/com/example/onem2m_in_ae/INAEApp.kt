package com.example.onem2m_in_ae

import android.app.Application
import com.example.onem2m_in_ae.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class INAEApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@INAEApp)
            viewModelModule
        }
    }
}