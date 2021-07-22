package com.example.onem2m_in_ae.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.onem2m_in_ae.databinding.ContainerListItemBinding
import com.example.onem2m_in_ae.model.ContainerImage
import com.example.onem2m_in_ae.view.ui.activity.AirPurifierActivity
import com.example.onem2m_in_ae.view.ui.activity.INAEViewModel

class ContainerImageRecyclerViewAdapter(
    val inaeViewModel: INAEViewModel, val containerImage
    : List<ContainerImage>
) :
    RecyclerView.Adapter<ContainerImageRecyclerViewAdapter.ContainerImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContainerImageViewHolder {
        return ContainerImageViewHolder(
            ContainerListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ContainerImageViewHolder, position: Int) {
        holder.bind(containerImage[position])
    }

    inner class ContainerImageViewHolder(val binding: ContainerListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ContainerImage) {
            binding.item = item.containerImage
            binding.containerItemImageView.setOnClickListener {
                inaeViewModel.callOnContainerImageEvent(item.containerImage)
            }
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return containerImage.size
    }

}