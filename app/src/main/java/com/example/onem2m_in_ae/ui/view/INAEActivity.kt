package com.example.onem2m_in_ae.ui.view

import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.ui.base.BaseActivity
import com.example.onem2m_in_ae.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class INAEActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private val inAEViewModel : INAEViewModel by viewModel()

    companion object {
        const val CSENAME = "Mobius"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.apply {
            lifecycleOwner = this@INAEActivity
        }

        inAEViewModel.getAEInfo()
    }
}