package com.example.onem2m_in_ae.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onem2m_in_ae.databinding.ContainerListItemBinding
import com.example.onem2m_in_ae.view.ui.activity.INAEViewModel

class ContainerImageRecyclerViewAdapter(
    val inaeViewModel: INAEViewModel, val containerImage
    : List<Int>
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

        fun bind(item: Int) {
            binding.item = item
            binding.containerItemImageView.setOnClickListener {
                inaeViewModel.callOnContainerImageEvent(item)
            }
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return containerImage.size
    }

}