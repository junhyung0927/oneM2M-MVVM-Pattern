package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityBoilerBinding
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.service.mqtt.MqttManager
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.APP_ID
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_DATA
import org.koin.android.viewmodel.ext.android.viewModel

class BoilerActivity : BaseActivity() {
    private val binding by binding<ActivityBoilerBinding>(R.layout.activity_boiler)
    private val boilerViewModel: BoilerViewModel by viewModel()
    private val mqttManager: MqttManager by lazy {
        MqttManager(applicationContext)
    }

    companion object {
        var containerResourceName = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@BoilerActivity

            val intent = intent
            val containerItem = intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
            item = containerItem.containerImage

            mqttManager.contentInstanceData.observe(this@BoilerActivity) {
                sensingDataLoadingAnimationBoilerActivity.visibility = View.GONE
                sensingDataTextViewBoilerActivity.visibility = View.VISIBLE
                containerItemImageViewBoilerActivity.visibility = View.VISIBLE
                scrollViewBoilerActivity.visibility = View.VISIBLE
                boilerDeleteAppCompactToggleButton.visibility = View.VISIBLE
                sensingDataHintTextViewBoilerActivity.visibility = View.VISIBLE
                containerNameTextViewBoilerActivity.visibility = View.VISIBLE

                containerNameTextViewBoilerActivity.text = containerItem.containerInstanceName
                if (!it.con.equals("on") && !it.con.equals("off")) {
                    sensingDataTextViewBoilerActivity.text = it.con
                }
            }

            boilerViewModel.apply {
                contentInstanceInfo.observe(this@BoilerActivity) {
                    println("장치 정보 가져오기")
                }
                contentInstanceControl.observe(this@BoilerActivity) {
                    println("장치 제어 성공")
                }
                deleteContainer.observe(this@BoilerActivity) {
                    println("장치 제거 성공")
                    startActivity(Intent(this@BoilerActivity, INAEActivity::class.java))
                }
                createSub.observe(this@BoilerActivity) {
                    println("createSub 성공")
                }

                getChildResourceInfo.observe(this@BoilerActivity) {
                    containerResourceName = getResourceName(it)
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

    override fun onStop() {
        mqttManager.unsubscribeToTopic(APP_ID, containerResourceName)
        super.onStop()
    }
}