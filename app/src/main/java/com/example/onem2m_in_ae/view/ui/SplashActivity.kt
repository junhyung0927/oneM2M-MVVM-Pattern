package com.example.onem2m_in_ae.view.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity, INAEActivity::class.java))
            finish()
        }
    }
}