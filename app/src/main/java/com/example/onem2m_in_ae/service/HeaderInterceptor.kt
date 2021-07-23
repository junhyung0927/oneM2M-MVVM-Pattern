package com.example.onem2m_in_ae.service

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("X-M2M-RI", "12345")
            .addHeader("X-M2M-Origin", "Swisoft")
            .build()

        proceed(newRequest)
    }
}