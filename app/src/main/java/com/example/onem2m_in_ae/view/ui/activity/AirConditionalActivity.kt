package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityAirconditionerBinding
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_in_ae.service.mqtt.MqttManager
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.APP_ID
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_DATA
import com.orhanobut.logger.Logger
import org.koin.android.viewmodel.ext.android.viewModel

class AirConditionalActivity : BaseActivity() {
    private val binding by binding<ActivityAirconditionerBinding>(R.layout.activity_airconditioner)
    private val airConditionalViewModel: AirConditionalViewModel by viewModel()
    private val mqttManager: MqttManager by lazy {
        MqttManager(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@AirConditionalActivity

            val intent = intent
            val containerItem = intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
            item = containerItem.containerImage

            airConditionalViewModel.getChildResourceInfo.observe(this@AirConditionalActivity) {
                val containerName = it.m2mUril
                    .filter { it.startsWith("Mobius/IYAHN_DEMO/") }
                    .map { it.split("/").last() }
                    .map { it.split(",") }
                if(containerItem.type.equals(ContainerType.AIRCONDITIONAL)) {
                    val containerResourceName = containerName.find { it.firstOrNull()!!.startsWith("tvoc") }
                }
            }

            mqttManager.connect(APP_ID)

            val RESOURCE_NAME = containerItem.containerInstanceName

            airConditionalViewModel.createSubscription(RESOURCE_NAME)
                .observe(this@AirConditionalActivity) {}

            airConditionalViewModel.getContainerInfo.observe(this@AirConditionalActivity) {
                println(it)
                if (RESOURCE_NAME.isNotEmpty()) {
                    airconditionerControlModeAppCompactToggleButton.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            airConditionalViewModel.deviceControl("on", RESOURCE_NAME)
                                .observe(this@AirConditionalActivity)
                                { println(it) }
                        } else {
                            airConditionalViewModel.deviceControl("off", RESOURCE_NAME)
                                .observe(this@AirConditionalActivity)
                                { println(it) }
                        }
                    }
                }
            }

            airconditionerSearchDataModeAppCompactButton.setOnClickListener {
                airConditionalViewModel.getContentInstanceInfo(RESOURCE_NAME)
                    .observe(this@AirConditionalActivity) {
                        Logger.d("CNT 조회: $it")
                    }
            }

            airconditionerDeleteAppCompactToggleButton.setOnClickListener {
                airConditionalViewModel.apply {
                    deleteAirConContainer(RESOURCE_NAME).observe(this@AirConditionalActivity) {
                        println("장치 제거 성공")
                    }

                    deleteDataBaseContainer(RESOURCE_NAME).observe(this@AirConditionalActivity) {
                        println("장치 데이터 베이스 제거 성공")
                        val intent = Intent(this@AirConditionalActivity, INAEActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    @Override
    override fun onBackPressed() {
        super.onBackPressed()
        mqttManager.unsubscribeToTopic(APP_ID)
    }
}