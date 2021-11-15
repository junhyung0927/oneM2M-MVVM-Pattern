package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityAirpurifierBinding
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.service.mqtt.MqttManager
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.APP_ID
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_DATA
import org.koin.android.viewmodel.ext.android.viewModel

class AirPurifierActivity : BaseActivity() {
    private val binding by binding<ActivityAirpurifierBinding>(R.layout.activity_airpurifier)
//    private val airPurifierViewModel: AirPurifierViewModel by viewModel()
    private val containerViewModel: ContainerViewModel by viewModel()
    private val mqttManager: MqttManager by lazy {
        MqttManager(applicationContext)
    }

    companion object {
        var containerResourceName = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@AirPurifierActivity

            val intent = intent
            val containerItem = intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
            item = containerItem.deviceImage

            mqttManager.contentInstanceData.observe(this@AirPurifierActivity) {
                sensingDataLoadingAnimationAirPurifierActivity.visibility = View.GONE
                sensingDataTextViewAirPurifierActivity.visibility = View.VISIBLE
                containerItemImageViewAirPurifierActivity.visibility = View.VISIBLE
                scrollViewAirPurifierActivity.visibility = View.VISIBLE
                airpurifierDeleteAppCompactToggleButton.visibility = View.VISIBLE
                sensingDataHintTextViewAirPurifierActivity.visibility = View.VISIBLE
                containerNameTextViewAirPurifierActivity.visibility = View.VISIBLE

                containerNameTextViewAirPurifierActivity.text = containerItem.deviceName

                if (!it.con.equals("on") && !it.con.equals("off")) {

                    sensingDataTextViewAirPurifierActivity.text = it.con
                }
            }

            containerViewModel.apply {
                contentInstanceInfo.observe(this@AirPurifierActivity) {
                    println("장치 정보 가져오기")
                }
                contentInstanceControl.observe(this@AirPurifierActivity) {
                    println("장치 제어 성공 $it")
                }
                deleteContainer.observe(this@AirPurifierActivity) {
                    println("장치 제거 성공")
                    startActivity(Intent(this@AirPurifierActivity, INAEActivity::class.java))
                }
                createSub.observe(this@AirPurifierActivity) {
                    println("createSub 성공")
                }

                getChildResourceInfo.observe(this@AirPurifierActivity) {
                    containerResourceName = getResourceName(it, "airpurifer")
                    mqttManager.getMqttClient(APP_ID, containerResourceName)
                    println("컨테이너 리소스 이름: ${containerResourceName}")
                    createSubscription(containerResourceName)
                    getContainerInfo.observe(this@AirPurifierActivity) {
                        if (containerResourceName.isNotEmpty()) {
                            airpurifierControlModeAppCompactToggleButton.setOnCheckedChangeListener { _, isChecked ->
                                val content = if (isChecked) {
                                    "on"
                                } else {
                                    "off"
                                }
                                deviceControl(content, containerResourceName)
                            }
                        }
                    }

                    airpurifierSearchDataModeAppCompactButton.setOnClickListener {
                        getContentInstanceInfo(containerResourceName)
                    }

                    airpurifierDeleteAppCompactToggleButton.setOnClickListener {
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