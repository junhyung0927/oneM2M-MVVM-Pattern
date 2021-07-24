package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.databinding.ActivityMainBinding
import com.example.onem2m_in_ae.util.EventObserver
import com.example.onem2m_in_ae.view.ui.adapter.ContainerImageRecyclerViewAdapter
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.viewmodel.ext.android.viewModel

class INAEActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private val inAEViewModel: INAEViewModel by viewModel()

    companion object {
        const val KEY_CONTAINER_IMAGE_DATA: String = "container_src"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        Logger.addLogAdapter(AndroidLogAdapter())

        val containerImageList = listOf(
            R.drawable.airconditioner,
            R.drawable.airpurifier,
            R.drawable.boiler
        )

        inAEViewModel.apply {
            createAE.observe(this@INAEActivity) {
                Logger.d("AE 생성: $it")
            }

            getAEInfo.observe(this@INAEActivity) {
//                Logger.d("AE 검색: $it")
            }

            getContainerInfo.observe(this@INAEActivity) {
                Logger.d("CON 검색: $it")
            }
            onContainerImageEvent.observe(this@INAEActivity, EventObserver
            {
                val destinationActivity = when (it) {
                    R.drawable.airconditioner -> AirConditionalActivity::class.java
                    R.drawable.airpurifier -> AirPurifierActivity::class.java
                    else -> BoilerActivity::class.java
                }

                val intent = Intent(this@INAEActivity, destinationActivity)
                intent.putExtra(KEY_CONTAINER_IMAGE_DATA, it)
                startActivity(intent)
            })
        }

        binding.apply {
            viewpager2INAEActivity.adapter = ContainerImageRecyclerViewAdapter(
                inAEViewModel, containerImageList
            )
        }
    }
}