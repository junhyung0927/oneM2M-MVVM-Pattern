package com.example.onem2m_in_ae.view.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityAirconditionerBinding
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEActivity.Companion.KEY_CONTAINER_IMAGE_DATA
import com.orhanobut.logger.Logger
import org.koin.android.viewmodel.ext.android.viewModel

class AirConditionalActivity: BaseActivity() {
    private val binding by binding<ActivityAirconditionerBinding>(R.layout.activity_airconditioner)
    private val airConditionalViewModel: AirConditionalViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@AirConditionalActivity

            val intent = intent
            val containerImageSrc = intent.getIntExtra(KEY_CONTAINER_IMAGE_DATA, 0)
            item = containerImageSrc

            airconditionerSearchDataModeAppCompactButton.setOnClickListener {
                airConditionalViewModel.getContentInstanceInfo().observe(this@AirConditionalActivity) {
                    Logger.d("CNT 조회: $it")
                }
            }
        }
    }
}