package com.example.onem2m_in_ae.ui.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseActivity : AppCompatActivity() {
    protected fun<VB: ViewDataBinding> binding(@LayoutRes resId: Int): Lazy<VB> =
        lazy { DataBindingUtil.setContentView<VB>(this, resId) }
}