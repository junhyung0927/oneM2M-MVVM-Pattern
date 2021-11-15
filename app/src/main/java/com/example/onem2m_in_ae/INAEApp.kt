package com.example.onem2m_in_ae

import android.app.Application
import com.example.onem2m_in_ae.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class INAEApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@INAEApp)
            modules(dataSourceModule)
            modules(repositoryModule)
            modules(getNetworkModule)
            modules(viewModelModule)
            modules(roomModule)
        }
    }
}