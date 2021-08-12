package com.example.onem2m_in_ae.view.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityContainerRegisterBinding
import com.example.onem2m_in_ae.model.ContainerType
import com.example.onem2m_in_ae.view.base.BaseActivity
import kotlinx.coroutines.test.withTestContext
import okhttp3.internal.notify
import org.koin.android.viewmodel.ext.android.viewModel

class ContainerRegisterActivity : BaseActivity() {
    private val binding by binding<ActivityContainerRegisterBinding>(R.layout.activity_container_register)
    private val containerRegisterViewModel: ContainerRegisterViewModel by viewModel()

    companion object {
        private var pos: Int = -1
        private val containerType = listOf(
            ContainerType.AIRCONDITIONAL,
            ContainerType.AIRPURIFIER,
            ContainerType.BOILER,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@ContainerRegisterActivity

            textInputEditContainerNameRegisterActivity.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (textInputEditContainerNameRegisterActivity.text!!.isEmpty()) {
                        textInputEditContainerNameRegisterActivity.error = "다시 입력해주세요."
                    } else {
                        textInputEditContainerNameRegisterActivity.error = null
                    }
                }
            })

            ArrayAdapter.createFromResource(
                this@ContainerRegisterActivity,
                R.array.container_list,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerContainerImageSelectRegisterActivity.adapter = adapter
            }

            spinnerContainerImageSelectRegisterActivity.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        containerImage = containerType.get(position).resId
                        pos = position
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                }

            binding.buttonContainerAddRegisterActivity.setOnClickListener {
                containerRegisterViewModel.apply {
                    createContainer(textInputEditContainerNameRegisterActivity.text.toString())
                        .observe(this@ContainerRegisterActivity) {
                            containerRegister(
                                containerImage as Int,
                                textInputEditContainerNameRegisterActivity.text.toString(),
                                containerType.get(pos)
                            )
                                .observe(this@ContainerRegisterActivity) {
                                    val intent = Intent(
                                        this@ContainerRegisterActivity,
                                        INAEActivity::class.java
                                    )
                                    startActivity(intent)
                                }
                        }
                }
            }
        }
    }
}