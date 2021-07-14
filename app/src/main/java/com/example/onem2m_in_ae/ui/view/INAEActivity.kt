package com.example.onem2m_in_ae.ui.view

import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.ui.base.BaseActivity
import com.example.onem2m_in_ae.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class INAEActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private val inAEViewModel : INAEViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this

        inAEViewModel.getAE()

//        inAEViewModel.getAEInfo.observe(this){
//            println(it.m2mAe.aei)
//        }
    }
}