package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.databinding.ActivityMainBinding
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.util.EventObserver
import com.example.onem2m_in_ae.view.ui.adapter.ContainerImageRecyclerViewAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.Serializable

class INAEActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private val inAEViewModel: INAEViewModel by viewModel()
    private var containerInstance: List<ContainerInstance> = listOf()
    private val adapter by lazy {
        ContainerImageRecyclerViewAdapter(inAEViewModel)
    }
    companion object {
        const val KEY_CONTAINER_DATA: String = "containerItem"
        const val CONTAINER_IMAGE: String = "container image"
        const val CONTAINER_NAME: String = "container name"

        private val containerImage = listOf(
            R.drawable.airconditioner,
            R.drawable.airpurifier,
            R.drawable.boiler
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        Logger.addLogAdapter(AndroidLogAdapter())

        inAEViewModel.apply {
            createAE.observe(this@INAEActivity) {
//                Logger.d("AE 생성: $it")
            }

            getAEInfo.observe(this@INAEActivity) {
//                Logger.d("조회 : $it")
            }

            getContainerDatabase.observe(this@INAEActivity) {
                if (it.isNotEmpty()) {
                    binding.explainTextViewINAEActivity.visibility = View.GONE
                    binding.viewpager2INAEActivity.visibility = View.VISIBLE
                }
                containerInstance = it
                adapter.submitList(it)
            }

            onContainerItemEvent.observe(this@INAEActivity, EventObserver
            {
                val destinationActivity = when (it.containerImage) {
                    R.drawable.airconditioner -> AirConditionalActivity::class.java
                    R.drawable.airpurifier -> AirPurifierActivity::class.java
                    else -> BoilerActivity::class.java
                }

                val intent = Intent(this@INAEActivity, destinationActivity)
                intent.putExtra(KEY_CONTAINER_DATA, it)
                startActivity(intent)
            })
        }

        binding.apply {
            viewpager2INAEActivity.adapter = adapter

            TabLayoutMediator(
                tabLayoutINAEActivity,
                viewpager2INAEActivity
            ) { tab, position -> }.attach()

            floatingButtonAddContainerINAEActivity.setOnClickListener {
                val intent = Intent(this@INAEActivity, ContainerRegisterActivity::class.java)
                intent.putExtra(CONTAINER_IMAGE, containerImage as Serializable)
                startActivity(intent)
            }
        }
    }
}
