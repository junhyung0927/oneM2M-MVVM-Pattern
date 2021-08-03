package com.example.onem2m_in_ae.view.ui.activity

import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityAirpurifierBinding
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_DATA

class AirPurifierActivity: BaseActivity() {
    private val binding by binding<ActivityAirpurifierBinding>(R.layout.activity_airpurifier)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@AirPurifierActivity

            val intent = intent
            val containerImageSrc = intent.getIntExtra(KEY_CONTAINER_DATA, 0)
        }
    }
}