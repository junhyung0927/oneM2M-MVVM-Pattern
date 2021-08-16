package com.example.onem2m_in_ae.model

import androidx.annotation.DrawableRes
import com.example.onem2m_in_ae.R

enum class ContainerType(@DrawableRes val resId: Int) {
    AIRCONDITIONAL(resId = R.drawable.airconditioner_2),
    AIRPURIFIER(resId = R.drawable.airpurifier_2),
    BOILER(resId = R.drawable.boiler_2)
}

