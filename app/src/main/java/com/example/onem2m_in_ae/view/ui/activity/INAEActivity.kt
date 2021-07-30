package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
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
    private var containerImage: MutableList<Int> = mutableListOf()
    private var containerInstance: MutableList<ContainerInstance> = mutableListOf()

    companion object {
        const val KEY_CONTAINER_IMAGE_DATA: String = "container_src"
        const val CONTAINER_IMAGE: String = "container image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        Logger.addLogAdapter(AndroidLogAdapter())

//        containerInstance = mutableListOf(
//            ContainerInstance(1, "에어컨", R.drawable.airconditioner),
//            ContainerInstance(2, "제습기", R.drawable.airpurifier),
//            ContainerInstance(3, "보일러", R.drawable.boiler)
//        )

        inAEViewModel.apply {

            createContainerInstance()

            createAE.observe(this@INAEActivity) {
                Logger.d("AE 생성: $it")
            }

//            insertContainerInstanceList(containerInstance)
            getContainerDatabase.observe(this@INAEActivity) {
                containerInstance = it
                println("테스트: ${it}")
                containerImage = mutableListOf(
                    it.get(0).containerImage,
                    it.get(1).containerImage,
                    it.get(2).containerImage,
                )
                binding.viewpager2INAEActivity.adapter = ContainerImageRecyclerViewAdapter(
                    inAEViewModel, containerInstance
                )
                TabLayoutMediator(
                    binding.tabLayoutINAEActivity,
                    binding.viewpager2INAEActivity
                ) { tab, position -> }.attach()
            }

            getAEInfo.observe(this@INAEActivity) {
                Logger.d("AE 검색: $it")
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
            floatingButtonAddContainerINAEActivity.setOnClickListener {
                val intent = Intent(this@INAEActivity, ContainerRegisterActivity::class.java)
                intent.putExtra(CONTAINER_IMAGE, containerImage as Serializable)
                startActivity(intent)
            }
        }
    }
}
