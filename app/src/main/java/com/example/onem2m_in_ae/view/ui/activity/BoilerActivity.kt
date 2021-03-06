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
//    private val boilerViewModel: BoilerViewModel by viewModel()
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
            lifecycleOwner = this@BoilerActivity

            val intent = intent
            val containerItem = intent.getSerializableExtra(KEY_CONTAINER_DATA) as ContainerInstance
            item = containerItem.deviceImage

            mqttManager.contentInstanceData.observe(this@BoilerActivity) {
                sensingDataLoadingAnimationBoilerActivity.visibility = View.GONE
                sensingDataTextViewBoilerActivity.visibility = View.VISIBLE
                containerItemImageViewBoilerActivity.visibility = View.VISIBLE
                scrollViewBoilerActivity.visibility = View.VISIBLE
                boilerDeleteAppCompactToggleButton.visibility = View.VISIBLE
                sensingDataHintTextViewBoilerActivity.visibility = View.VISIBLE
                containerNameTextViewBoilerActivity.visibility = View.VISIBLE

                containerNameTextViewBoilerActivity.text = containerItem.deviceName
                if (!it.con.equals("on") && !it.con.equals("off")) {
                    sensingDataTextViewBoilerActivity.text = it.con
                }
            }

            containerViewModel.apply {
                contentInstanceInfo.observe(this@BoilerActivity) {
                    println("?????? ?????? ????????????")
                }
                contentInstanceControl.observe(this@BoilerActivity) {
                    println("?????? ?????? ??????")
                }
                deleteContainer.observe(this@BoilerActivity) {
                    println("?????? ?????? ??????")
                    startActivity(Intent(this@BoilerActivity, INAEActivity::class.java))
                }
                createSub.observe(this@BoilerActivity) {
                    println("createSub ??????")
                }

                getChildResourceInfo.observe(this@BoilerActivity) {
                    containerResourceName = getResourceName(it, "boiler")
                    println("???????????? ????????? ??????: ${containerResourceName}")
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
                        deleteContainer(containerItem.deviceName)
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