package com.example.onem2m_in_ae.ui.view

import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.ui.base.BaseActivity
import com.example.onem2m_in_ae.databinding.ActivityMainBinding
import com.example.onem2m_in_ae.model.ContainerImage
import com.example.onem2m_in_ae.ui.ContainerImageRecyclerViewAdapter
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

        binding.viewpager2ActivityMain.adapter =
            ContainerImageRecyclerViewAdapter(containerImageList)

        inAEViewModel.apply {
            createAE().observe(this@INAEActivity) {
                Logger.d("AE 생성: $it")
            }

            getAEInfo().observe(this@INAEActivity) {
                Logger.d("AE 검색: $it")
            }
        }

        binding.apply {
            containerControlButton.setOnClickListener {
                inAEViewModel.getContentInstanceInfo().observe(this@INAEActivity) {
                    Logger.d("CNT 조회: $it")
                }
            }
        }

//        binding.ledControlButton.setOnCheckedChangeListener { _, isChecked ->
//            if(isChecked) {
//                inAEViewModel.getContentInstanceInfo().observe(this) {
//                    Logger.d("CNT 검색: $it")
//                }
//            } else {
//                println("Trun off")
//            }
//        }
    }
}