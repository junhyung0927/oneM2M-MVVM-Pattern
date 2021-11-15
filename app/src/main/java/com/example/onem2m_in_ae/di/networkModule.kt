package com.example.onem2m_in_ae.di

import com.example.onem2m_in_ae.service.HeaderInterceptor
import com.example.onem2m_in_ae.service.OneM2MService
import com.example.onem2m_in_ae.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val getNetworkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.BODY
                }
            })
            .build()
    }

    single {
        GsonConverterFactory
            .create(GsonBuilder().setLenient().create()) as Converter.Factory
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(get())
            .build()
            .create(OneM2MService::class.java)
    }
}
