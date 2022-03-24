package com.example.onem2m_in_ae.model

import androidx.annotation.DrawableRes
import com.example.onem2m_in_ae.R

enum class DeviceType(@DrawableRes val resId: Int) {
    AIRCONDITIONAL(resId = R.drawable.airconditioner),
    AIRPURIFIER(resId = R.drawable.airpurifier),
    BOILER(resId = R.drawable.boiler)
}


