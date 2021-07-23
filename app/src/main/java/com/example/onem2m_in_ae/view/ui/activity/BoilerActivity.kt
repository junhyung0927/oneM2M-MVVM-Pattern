package com.example.onem2m_in_ae.view.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityBoilerBinding
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.base.BaseFragment
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_IMAGE_DATA

class BoilerActivity: BaseActivity() {
    private val binding by binding<ActivityBoilerBinding>(R.layout.activity_boiler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@BoilerActivity

            val intent = intent
            val containerImageSrc = intent.getIntExtra(KEY_CONTAINER_IMAGE_DATA, 0)
        }
    }
}