package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityAirpurifierBinding
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.service.mqtt.MqttManager
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_DATA
import org.koin.android.viewmodel.ext.android.viewModel

class AirPurifierActivity : BaseActivity() {
    private val binding by binding<ActivityAirpurifierBinding>(R.layout.activity_airpurifier)
    private val airPurifierViewModel: AirPurifierViewModel by viewModel()
    private val mqttManager: MqttManager by lazy {
        MqttManager(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@AirPurifierActivity

            val intent = intent
            val containerItem = intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
            item = containerItem.containerImage

            mqttManager.contentInstanceData.observe(this@AirPurifierActivity) {
                sensingDataTextViewAirPurifierActivity.text = it.con
            }

            airPurifierViewModel.apply {
                contentInstanceInfo.observe(this@AirPurifierActivity) { }
                contentInstanceControl.observe(this@AirPurifierActivity) {
                    println("장치 제어 성공")
                }
                deleteContainer.observe(this@AirPurifierActivity) {
                    startActivity(Intent(this@AirPurifierActivity, INAEActivity::class.java))
                }
                createSub.observe(this@AirPurifierActivity) {
                    println("createSub 성공")
                    mqttManager.getMqttClient(INAEActivity.APP_ID)
                }

                getChildResourceInfo.observe(this@AirPurifierActivity) {
                    val containerResourceName = getResourceName(it)
                    println("컨테이너 리소스 이름: ${containerResourceName}")
                    createSubscription(containerResourceName)
                    getContainerInfo.observe(this@AirPurifierActivity) {
                        if (containerResourceName.isNotEmpty()) {
                            airPurifierControlModeAppCompactToggleButton.setOnCheckedChangeListener { _, isChecked ->
                                val content = if (isChecked) {
                                    "on"
                                } else {
                                    "off"
                                }
                                deviceControl(content, containerResourceName)
                            }
                        }
                    }

                    airPurifierSearchDataModeAppCompactButton.setOnClickListener {
                        getContentInstanceInfo(containerResourceName)
                    }

                    airPurifierDeleteAppCompactToggleButton.setOnClickListener {
                        deleteDataBaseContainer(containerItem.containerInstanceName)
                    }
                }
            }
        }
    }
}