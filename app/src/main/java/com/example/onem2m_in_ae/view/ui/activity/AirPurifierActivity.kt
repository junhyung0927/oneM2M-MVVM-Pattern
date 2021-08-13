package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityAirpurifierBinding
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_DATA
import com.orhanobut.logger.Logger
import org.koin.android.viewmodel.ext.android.viewModel

class AirPurifierActivity : BaseActivity() {
    private val binding by binding<ActivityAirpurifierBinding>(R.layout.activity_airpurifier)
    private val airPurifierViewModel: AirPurifierViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@AirPurifierActivity

            val intent = intent
            val containerItem = intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
            item = containerItem.containerImage

            val RESOURCE_NAME = containerItem.containerInstanceName

            airPurifierViewModel.getContainerInfo.observe(this@AirPurifierActivity)
            {
                if (RESOURCE_NAME.isNotEmpty()) {
                    airPurifierControlModeAppCompactToggleButton.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            airPurifierViewModel.deviceControl("on", RESOURCE_NAME)
                                .observe(this@AirPurifierActivity) { println(it) }
                        } else {
                            airPurifierViewModel.deviceControl("off", RESOURCE_NAME)
                                .observe(this@AirPurifierActivity) { println(it) }
                        }
                    }
                }
            }

            airPurifierSearchDataModeAppCompactButton.setOnClickListener {
                airPurifierViewModel.getContentInstanceInfo(RESOURCE_NAME)
                    .observe(this@AirPurifierActivity) {
                        Logger.d("제습기 데이터 조회")
                    }
            }

            airPurifierDeleteAppCompactToggleButton.setOnClickListener {
                airPurifierViewModel.apply {
                    deleteDataBaseContainer(RESOURCE_NAME).observe(this@AirPurifierActivity) {
                        println("해당 장치 데이터베이스 제거 성공")
                        val intent = Intent(this@AirPurifierActivity, INAEActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}