package com.example.onem2m_in_ae.view.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DeviceDeleteDialogFragment : DialogFragment() {
    companion object {
        private val RESOURCE_NAME = "resource name"

        //resource name을 받아와야 하나..?
        fun newInstance(resourceName: String) =
            DeviceDeleteDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(RESOURCE_NAME, resourceName)
                }
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        return super.onCreateDialog(savedInstanceState)
    }
}