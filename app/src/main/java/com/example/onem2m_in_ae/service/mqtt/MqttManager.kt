package com.example.onem2m_in_ae.service.mqtt

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onem2m_in_ae.model.ContentInstanceMqttData
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import java.lang.Exception

class MqttManager(context: Context) {
    private val serverURI = "tcp://192.168.10.62:1883"
    private val mqttClient: MqttAndroidClient = MqttAndroidClient(context, serverURI, MqttClient.generateClientId())
    private val mqttConnectOptions = MqttConnectOptions()
    private val _contentInstanceData: MutableLiveData<ContentInstanceMqttData> = MutableLiveData()
    val contentInstanceData: LiveData<ContentInstanceMqttData> = _contentInstanceData

    fun mqttConnect(appId: String, containerName: String) {
        try {
            mqttConnectOptions.apply {
                isAutomaticReconnect = true
                isCleanSession = true
            }

            mqttClient.connect(mqttConnectOptions, null, object: IMqttActionListener{
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    val req_topic = "/oneM2M/req/Mobius2/${appId}_${containerName}/json"
                    subscribeToTopic(req_topic)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    println("연결 실패")
                }

            })
        } catch (e: Exception) {
            println("연결: ${e}")
        }
    }

    fun getMqttClient(appId: String, containerName: String) {
        mqttConnect(appId, containerName)
        try {
            mqttClient.setCallback(object : MqttCallbackExtended {
                override fun connectionLost(cause: Throwable?) {
                    println("연결 lost")
                }

                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    println("message arrived: ${message.toString()}")
                    _contentInstanceData.value = MqttRepository().parseMessage(message)

                    val retrqi = MqttClientRequestParser().notificationJsonParse(message.toString())
                    val responseMessage = MqttClientRequest().notificationResponse(retrqi)
                    val res_message = MqttMessage(responseMessage.toByteArray())

                    try {
                        val resp_topic = "/oneM2M/resp/Mobius2/${appId}_${containerName}/json"
                        mqttClient.publish(resp_topic, res_message)
                    } catch (e: MqttException) {
                        e.printStackTrace()
                    }
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {
                    println("deliveryComplete: ${token}")
                }

                override fun connectComplete(reconnect: Boolean, serverURI: String?) {
                    println("MQTT 연결 성공")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
            println("extend 에러: " + e.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            println("connect: ${e}")
        }
    }

    fun subscribeToTopic(topic: String, qos: Int = 0) {
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
            println("sub 에러: " + e.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            println("sub to topic: ${e}")
        }
    }

    fun unsubscribeToTopic(appId: String, containerName: String) {
        val topic = "/oneM2M/req/Mobius2/${appId}_${containerName}/json"
//        val topic = "/oneM2M/req/Mobius2/${appId}_${containerName}/#"

        if (mqttClient.isConnected)
            try {
                println("구독 해제")
                mqttClient.unsubscribe(topic)
                mqttClient.close()
            } catch (e: MqttException) {
                e.printStackTrace()
                println("unsub 에러: " + e.toString())
            }
    }
}