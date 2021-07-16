package com.example.onem2m_in_ae.ui.base

import androidx.lifecycle.ViewModel
import java.lang.Exception

open class BaseViewModel : ViewModel(){
    suspend fun <T> handle(call: suspend () -> T): T?{
        try {
            return call.invoke()
        } catch (e: Exception) {
            onError(e)
        }
        return null
    }

    open fun onError(e: Exception) {

    }
}