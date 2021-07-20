package com.example.onem2m_in_ae.ui.view

import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.ui.base.BaseActivity
import com.example.onem2m_in_ae.databinding.ActivityMainBinding
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.json.JSONObject
import org.koin.android.viewmodel.ext.android.viewModel

class INAEActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private val inAEViewModel: INAEViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        Logger.addLogAdapter(AndroidLogAdapter())


        inAEViewModel.apply {
            createAEInfo().observe(this@INAEActivity) {
                Logger.d("AE 생성: $it")
            }

            getAE().observe(this@INAEActivity) {
                Logger.d("AE 검색: $it")
            }
        }

        binding.ledControlButton.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                inAEViewModel.
            }
        }
    }
}