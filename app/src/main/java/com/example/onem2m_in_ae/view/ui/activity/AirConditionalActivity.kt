package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.TimingLogger
import android.view.View
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

    companion object {
        var containerResourceName = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@AirConditionalActivity

            val intent = intent
            val containerItem = intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
            item = containerItem.deviceImage

            mqttManager.contentInstanceData.observe(this@AirConditionalActivity) {
                sensingDataLoadingAnimationAirConditionerActivity.visibility = View.GONE
                sensingDataTextViewAirConditionerActivity.visibility = View.VISIBLE
                containerItemImageViewAirConditionerActivity.visibility = View.VISIBLE
                scrollViewAirConditionerActivity.visibility = View.VISIBLE
                airconditionerDeleteAppCompactToggleButton.visibility = View.VISIBLE
                sensingDataHintTextViewAirConditionerActivity.visibility = View.VISIBLE
                containerNameTextViewAirConditionerActivity.visibility = View.VISIBLE

                containerNameTextViewAirConditionerActivity.text = containerItem.deviceName
                if (!it.con.equals("on") && !it.con.equals("off")) {
                    sensingDataTextViewAirConditionerActivity.text = it.con
                }
            }

            airConditionalViewModel.apply {
                contentInstanceInfo.observe(this@AirConditionalActivity) {
                    println("장치 정보 가져오기")
                }

                contentInstanceControl.observe(this@AirConditionalActivity) {
                    println("장치 제어 성공")
                }

                deleteContainer.observe(this@AirConditionalActivity) {
                    println("장치 제거 성공")
                    startActivity(Intent(this@AirConditionalActivity, INAEActivity::class.java))
                }

                createSub.observe(this@AirConditionalActivity) {
                    println("createSub 성공")
                }

                getChildResourceInfo.observe(this@AirConditionalActivity) {
                    containerResourceName = getResourceName(it)
                    println("컨테이너 리소스 이름: ${containerResourceName}")
                    createSubscription(containerResourceName)
                    mqttManager.getMqttClient(APP_ID, containerResourceName)

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
                        deleteDataBaseContainer(containerItem.deviceName)
                    }
                }
            }
        }
    }

    override fun onStop() {
        mqttManager.unsubscribeToTopic(APP_ID, containerResourceName)
        super.onStop()
    }
}

