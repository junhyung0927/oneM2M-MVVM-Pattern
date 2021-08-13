package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityAirconditionerBinding
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.service.mqtt.MqttManager
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.APP_ID
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_DATA
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

            airConditionalViewModel.apply {
                contentInstanceInfo.observe(this@AirConditionalActivity) { }
                contentInstanceControl.observe(this@AirConditionalActivity) {
                    println("장치 제어 성공")
                }
                deleteContainer.observe(this@AirConditionalActivity) {
                    val intent =
                        Intent(this@AirConditionalActivity, INAEActivity::class.java)
                    startActivity(intent)
                }
                createSub.observe(this@AirConditionalActivity) {
                    mqttManager.connect(APP_ID)
                }

                getChildResourceInfo.observe(this@AirConditionalActivity) {
                    val containerResourceName = getResourceName(it)
                    println("컨테이너 리소스 이름: ${containerResourceName}")
                    createSubscription(containerResourceName)
                    getContainerInfo.observe(this@AirConditionalActivity) {
                        if (containerResourceName.isNotEmpty()) {
                            airconditionerControlModeAppCompactToggleButton.setOnCheckedChangeListener { _, isChecked ->
                                val content = if (isChecked) {
                                    "on"
                                } else {
                                    "off"
                                }
                                deviceControl(content, containerResourceName)
                            }
                        }
                    }

                    airconditionerSearchDataModeAppCompactButton.setOnClickListener {
                        getContentInstanceInfo(containerResourceName)
                    }

                    airconditionerDeleteAppCompactToggleButton.setOnClickListener {
                        deleteDataBaseContainer(containerResourceName)
                    }
                }
            }
        }
    }

    override fun onPause() {
        mqttManager.unsubscribeToTopic(APP_ID)
        super.onPause()
    }
}

