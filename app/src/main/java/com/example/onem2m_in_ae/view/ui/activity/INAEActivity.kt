package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.view.base.BaseActivity
import com.example.onem2m_in_ae.databinding.ActivityMainBinding
import com.example.onem2m_in_ae.model.ContainerImage
import com.example.onem2m_in_ae.util.Event
import com.example.onem2m_in_ae.util.EventObserver
import com.example.onem2m_in_ae.view.ContainerImageRecyclerViewAdapter
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.viewmodel.ext.android.viewModel

class INAEActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private val inAEViewModel: INAEViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        Logger.addLogAdapter(AndroidLogAdapter())

        val containerImageList = listOf(
            ContainerImage(R.drawable.airconditioner),
            ContainerImage(R.drawable.airpurifier),
            ContainerImage(R.drawable.boiler)
        )

        inAEViewModel.apply {
            createAE().observe(this@INAEActivity) {
                Logger.d("AE 생성: $it")
            }

            getAEInfo().observe(this@INAEActivity) {
                Logger.d("AE 검색: $it")
            }
        }

        binding.apply {
            viewpager2ActivityMain.adapter = ContainerImageRecyclerViewAdapter(
                inAEViewModel, containerImageList
            )

            viewpager2ActivityMain.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    when (position) {
                        0 -> {
                            inAEViewModel.onContainerImageEvent.observe(
                                this@INAEActivity, EventObserver
                                {
                                    val intent = Intent(
                                        this@INAEActivity, AirConditionalActivity::class.java
                                    )
                                    startActivity(intent)
                                })
                        }
                        1 -> {
                            inAEViewModel.onContainerImageEvent.observe(
                                this@INAEActivity, EventObserver
                                {
                                    val intent = Intent(
                                        this@INAEActivity, AirPurifierActivity::class.java
                                    )
                                    startActivity(intent)
                                })
                        }
                        2 -> {
                            inAEViewModel.onContainerImageEvent.observe(
                                this@INAEActivity, { event: Event<Int> ->
                                    val intent = Intent(
                                        this@INAEActivity, BoilerActivity::class.java
                                    )
                                    startActivity(intent)
                                }
                            )
                        }
                    }
                }
            })
            containerControlButton.setOnClickListener {
                inAEViewModel.getContentInstanceInfo().observe(this@INAEActivity) {
                    Logger.d("CNT 조회: $it")
                }
            }
        }

    }
}