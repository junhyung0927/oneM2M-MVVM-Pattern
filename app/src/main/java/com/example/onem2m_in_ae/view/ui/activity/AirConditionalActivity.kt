package com.example.onem2m_in_ae.view.ui.activity

import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityAirconditionerBinding
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_DATA
import com.orhanobut.logger.Logger
import org.koin.android.viewmodel.ext.android.viewModel

class AirConditionalActivity : BaseActivity() {
    private val binding by binding<ActivityAirconditionerBinding>(R.layout.activity_airconditioner)
    private val airConditionalViewModel: AirConditionalViewModel by viewModel()

    companion object {
        private var RESOURCE_NAME: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@AirConditionalActivity

            val intent = intent
            val containerItem = intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
//            val containerImageSrc = intent.getIntExtra(KEY_CONTAINER_DATA, 0)
            item = containerItem.containerImage

            airConditionalViewModel.getContainerInfo.observe(this@AirConditionalActivity) {
                RESOURCE_NAME = "aircon"
                if (RESOURCE_NAME.isNotEmpty()) {
                    airconditionerControlModeAppCompactToggleButton.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            airConditionalViewModel.deviceControl("on", RESOURCE_NAME)
                                .observe(this@AirConditionalActivity)
                                {
                                    println(it)
                                }
                        } else {
                            airConditionalViewModel.deviceControl("off", RESOURCE_NAME)
                                .observe(this@AirConditionalActivity) {
                                    println(it)
                                }
                        }
                    }
                }
            }

            airconditionerSearchDataModeAppCompactButton.setOnClickListener {
                airConditionalViewModel.getContentInstanceInfo()
                    .observe(this@AirConditionalActivity) {
                        Logger.d("CNT 조회: $it")
                    }
            }
        }
    }
}