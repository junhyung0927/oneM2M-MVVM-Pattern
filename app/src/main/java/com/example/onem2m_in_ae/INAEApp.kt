package com.example.onem2m_in_ae

import android.app.Application
import com.example.onem2m_in_ae.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class INAEApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                dataSourceModule,
                dispatcherModule,
                useCaseModule,
                repositoryModule,
                getNetworkModule,
                viewModelModule,
                roomModule
            )
            androidContext(this@INAEApp)
        }
    }
}