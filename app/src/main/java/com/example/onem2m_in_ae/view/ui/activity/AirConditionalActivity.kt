package com.example.onem2m_in_ae.view.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityAirconditionerBinding
import com.example.onem2m_in_ae.view.base.BaseActivity

class AirConditionalActivity: BaseActivity() {
    private val binding by binding<ActivityAirconditionerBinding>(R.layout.activity_airconditioner)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@AirConditionalActivity
        }
    }
}