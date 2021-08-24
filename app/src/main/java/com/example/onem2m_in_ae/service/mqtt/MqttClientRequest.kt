package com.example.onem2m_in_ae.service.mqtt

class MqttClientRequest {
    fun notificationResponse(response: String): String {
        val responseMessage =
            """
            {"rsc":"2000",
            "rqi":"$response",
            "pc":""}
            """.trimIndent()

        return responseMessage
    }
}