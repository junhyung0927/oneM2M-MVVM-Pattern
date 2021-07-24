package com.example.onem2m_in_ae.util

class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) { //이벤트가 이미 처리되었다면 null 반환
            null
        } else {
            hasBeenHandled = true //이벤트가 처리되었다고 표시한 후에 값을 반환
            content
        }
    }

    //이번트의 처리 여부에 상관 없이 값을 반환
    fun peekCount(): T = content
}