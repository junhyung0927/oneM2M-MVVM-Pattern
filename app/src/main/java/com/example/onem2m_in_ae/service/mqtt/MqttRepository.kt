package com.example.onem2m_in_ae.service.mqtt

import com.example.onem2m_in_ae.model.ContentInstanceMqttData
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.json.JSONObject
import java.lang.Exception

class MqttRepository {
    fun parseMessage(message: MqttMessage?): ContentInstanceMqttData {
        try {
            val jsonObject = JSONObject(message.toString())
            val pc = JSONObject(jsonObject.toString()).getJSONObject("pc")
            val m2mSgn = JSONObject(pc.toString()).getJSONObject("m2m:sgn")
            val nev = JSONObject(m2mSgn.toString()).getJSONObject("nev")
            val rep = JSONObject(nev.toString()).getJSONObject("rep")
            val m2m_cin = JSONObject(rep.toString()).getJSONObject("m2m:cin")

            return ContentInstanceMqttData(
                m2m_cin.getString("con")
            )

        } catch (e: Exception) {
            println("에러: ${e}")
            return ContentInstanceMqttData("연결중")
        }
    }
}