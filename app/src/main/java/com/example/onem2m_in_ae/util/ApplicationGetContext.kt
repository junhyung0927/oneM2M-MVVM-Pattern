package com.example.onem2m_in_ae.util

import android.app.Application
import android.content.Context

class ApplicationGetContext : Application() {
    private var mContext: Context? = null

    init {
        mContext = this
    }

    fun getContext(): Context? {
        return mContext
    }
}