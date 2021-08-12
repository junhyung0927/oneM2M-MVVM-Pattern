package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityBoilerBinding
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_DATA
import com.orhanobut.logger.Logger
import org.koin.android.viewmodel.ext.android.viewModel

class BoilerActivity : BaseActivity() {
    private val binding by binding<ActivityBoilerBinding>(R.layout.activity_boiler)
    private val boilerViewModel: BoilerViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@BoilerActivity

            val intent = intent
            val containerItem = intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
            item = containerItem.containerImage

            val RESOURCE_NAME = containerItem.containerInstanceName

            boilerViewModel.getContainerInfo.observe(this@BoilerActivity)
            {
                if (RESOURCE_NAME.isNotEmpty()) {
                    boilerControlModeAppCompactToggleButton.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            boilerViewModel.deviceControl("on", RESOURCE_NAME)
                                .observe(this@BoilerActivity)
                                { println(it) }
                        } else {
                            boilerViewModel.deviceControl("off", RESOURCE_NAME)
                                .observe(this@BoilerActivity)
                                { println(it) }
                        }
                    }
                }
            }

            boilerSearchDataModeAppCompactButton.setOnClickListener {
                boilerViewModel.getContentInstanceInfo(RESOURCE_NAME).observe(this@BoilerActivity)
                { Logger.d("보일러 데이터 조회") }
            }

            boilerDeleteAppCompactToggleButton.setOnClickListener {
                boilerViewModel.apply {
                    deleteBoilerContainer(RESOURCE_NAME).observe(this@BoilerActivity)
                    { println("장치 제거 성공") }

                    deleteDataBaseContainer(RESOURCE_NAME).observe(this@BoilerActivity) {
                        println("해당 장치 데이터베이스 제거 성공")
                        val intent = Intent(this@BoilerActivity, INAEActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}