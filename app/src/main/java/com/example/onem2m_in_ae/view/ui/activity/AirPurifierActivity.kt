package com.example.onem2m_in_ae.view.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityAirpurifierBinding
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.base.BaseFragment
import org.koin.android.ext.android.bind

class AirPurifierActivity: BaseActivity() {
    private val binding by binding<ActivityAirpurifierBinding>(R.layout.activity_airpurifier)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@AirPurifierActivity
        }
    }
}