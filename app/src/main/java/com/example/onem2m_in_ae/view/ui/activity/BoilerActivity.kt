package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityBoilerBinding
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.service.mqtt.MqttManager
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_DATA
import org.koin.android.viewmodel.ext.android.viewModel

class BoilerActivity : BaseActivity() {
    private val binding by binding<ActivityBoilerBinding>(R.layout.activity_boiler)
    private val boilerViewModel: BoilerViewModel by viewModel()
    private val mqttManager: MqttManager by lazy {
        MqttManager(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@BoilerActivity

            val intent = intent
            val containerItem = intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
            item = containerItem.containerImage

            mqttManager.contentInstanceData.observe(this@BoilerActivity) {
                sensingDataTextViewBoilerActivity.text = it.con
            }

            boilerViewModel.apply {
                contentInstanceInfo.observe(this@BoilerActivity) { }
                contentInstanceControl.observe(this@BoilerActivity) {
                    println("장치 제어 성공")
                }
                deleteContainer.observe(this@BoilerActivity) {
                    startActivity(Intent(this@BoilerActivity, INAEActivity::class.java))
                }
                createSub.observe(this@BoilerActivity) {
                    println("createSub 성공")
                }

                getChildResourceInfo.observe(this@BoilerActivity) {
                    val containerResourceName = getResourceName(it)
                    println("컨테이너 리소스 이름: ${containerResourceName}")
                    createSubscription(containerResourceName)
                    mqttManager.getMqttClient(INAEActivity.APP_ID, containerResourceName)
                    getContainerInfo.observe(this@BoilerActivity) {
                        if (containerResourceName.isNotEmpty()) {
                            boilerControlModeAppCompactToggleButton.setOnCheckedChangeListener { _, isChecked ->
                                val content = if (isChecked) {
                                    "on"
                                } else {
                                    "off"
                                }
                                deviceControl(content, containerResourceName)
                            }
                        }
                    }

                    boilerSearchDataModeAppCompactButton.setOnClickListener {
                        getContentInstanceInfo(containerResourceName)
                    }

                    boilerDeleteAppCompactToggleButton.setOnClickListener {
                        deleteDataBaseContainer(containerItem.containerInstanceName)
                    }
                }
            }
        }
    }
}