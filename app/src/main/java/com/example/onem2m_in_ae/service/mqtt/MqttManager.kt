package com.example.onem2m_in_ae.service.mqtt

import android.content.Context
import android.util.Log
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import java.lang.Exception

class MqttManager {
    private lateinit var mqttClient: MqttAndroidClient

    companion object {
        const val TAG = "AndroidMqttClient"
    }

    fun connect(context: Context) {
        val serverURI = "tcp://192.168.10.62:1883"
        mqttClient = MqttAndroidClient(context, serverURI, MqttClient.generateClientId())
        mqttClient.connect()
        try {
            mqttClient.setCallback(object : MqttCallbackExtended {
                override fun connectionLost(cause: Throwable?) {
                    println("연결 lost")
                }

                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    println("message arrived")
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {}

                override fun connectComplete(reconnect: Boolean, serverURI: String?) {
                    val topic = "/Mobius/IYAHN_DEMO/co2/la"
                    subscribeToTopic(topic)
                    println("연결 성공")
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
                    println("sub 성공")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    println("sub 실패")
                }
            })
        } catch (e: MqttException){
            e.printStackTrace()
        }
    }

    fun unsubscribeToTopic(topic: String) {
        try {
            mqttClient.unsubscribe(topic, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    println("sub 연결 해제 성공")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    println("sub 연결 해제 실패")
                }
            })
        } catch (e: MqttException){
            e.printStackTrace()
        }
    }
}