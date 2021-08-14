package com.example.onem2m_in_ae.service.mqtt

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onem2m_in_ae.model.ContentInstanceMqttData
import com.example.onem2m_in_ae.util.ApplicationGetContext
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import org.json.JSONObject
import java.lang.Exception

class MqttManager(context: Context) {
    private val serverURI = "tcp://192.168.10.62:1883"
    private val mqttClient: MqttAndroidClient = MqttAndroidClient(context, serverURI, MqttClient.generateClientId())
    private val _contentInstanceData: MutableLiveData<ContentInstanceMqttData> = MutableLiveData()
    val contentInstanceData: LiveData<ContentInstanceMqttData> = _contentInstanceData

    fun connect(appId: String) {
        mqttClient.connect()
        try {
            mqttClient.setCallback(object : MqttCallbackExtended {
                override fun connectionLost(cause: Throwable?) {
                    println("연결 lost")
                }

                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    println("message arrived: ${message.toString()}")
                    _contentInstanceData.value = MqttRepository().parseMessage(message)
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {}

                override fun connectComplete(reconnect: Boolean, serverURI: String?) {
                    val topic = "/oneM2M/req/Mobius2/${appId}_sub/json"
                    subscribeToTopic(topic)
                    println("MQTT 연결 성공")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun subscribeToTopic(topic: String, qos: Int = 1) {
        try {
            mqttClient.subscribe(topic, qos, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    println("토픽: ${topic}")
                    println("sub 성공")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    println("sub 실패")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun unsubscribeToTopic(appId: String) {
        val topic = "/oneM2M/req/Mobius2/${appId}_sub/json"
        if (mqttClient.isConnected)
            try {
                println("구독 해제")
                mqttClient.unsubscribe(topic)
            } catch (e: MqttException) {
                e.printStackTrace()
            }
    }
}