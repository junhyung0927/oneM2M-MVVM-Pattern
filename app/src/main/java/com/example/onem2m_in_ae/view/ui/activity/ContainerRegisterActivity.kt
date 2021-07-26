package com.example.onem2m_in_ae.view.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.example.onem2m_in_ae.R
import com.example.onem2m_in_ae.databinding.ActivityContainerRegisterBinding
import com.example.onem2m_in_ae.view.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ContainerRegisterActivity : BaseActivity() {
    private val binding by binding<ActivityContainerRegisterBinding>(R.layout.activity_container_register)
    private val containerRegisterViewModel: ContainerRegisterViewModel by viewModel()
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
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if(textInputEditContainerNameRegisterActivity.text!!.isEmpty()) {
                        textInputEditContainerNameRegisterActivity.error = "다시 입력해주세요."
                    } else {
                        textInputEditContainerNameRegisterActivity.error = null
                    }
                }
            })
        }
    }
}