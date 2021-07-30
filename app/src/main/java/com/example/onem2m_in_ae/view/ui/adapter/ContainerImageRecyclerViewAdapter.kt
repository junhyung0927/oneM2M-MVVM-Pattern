package com.example.onem2m_in_ae.view.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.onem2m_in_ae.databinding.ContainerListItemBinding
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_in_ae.view.ui.activity.INAEViewModel

class ContainerImageRecyclerViewAdapter(
    val inaeViewModel: INAEViewModel
) :
    ListAdapter<ContainerInstance, ContainerImageRecyclerViewAdapter.ContainerImageViewHolder>(
        DIFF_CALLBACK
    ) {
    private var instanceList = listOf<ContainerInstance>()

    companion object {
        val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<ContainerInstance>() {
            override fun areItemsTheSame(
                oldItem: ContainerInstance,
                newItem: ContainerInstance
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ContainerInstance,
                newItem: ContainerInstance
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContainerImageViewHolder {
        return ContainerImageViewHolder(
            ContainerListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ContainerImageViewHolder, position: Int) {
        holder.bind(
            instanceList[position]
        )
    }

    inner class ContainerImageViewHolder(val binding: ContainerListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ContainerInstance) {
            binding.itemImage = item.containerImage
            binding.itemName = item.containerInstanceName
            binding.containerItemImageViewAirConditionerActivity.setOnClickListener {
                inaeViewModel.callOnContainerImageEvent(item.containerImage)
            }
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return instanceList.size
    }

    override fun submitList(list: List<ContainerInstance>?) {
        super.submitList(list)
        instanceList = list as List<ContainerInstance>
        notifyDataSetChanged()
    }

    fun getContainerInstance(containerInstance: List<ContainerInstance>)
            : List<ContainerInstance> {
        return containerInstance
    }
}

//class ContentDiffUtil(
//    private val oldList: List<ContainerInstance>, private val newList: List<ContainerInstance>
//) : DiffUtil.Callback() {
//    override fun getOldListSize(): Int = oldList.size
//
//    override fun getNewListSize(): Int = newList.size
//
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return oldList[oldItemPosition].containerInstanceName == newList[newItemPosition].containerInstanceName
//    }
//
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return oldList[oldItemPosition] == newList[newItemPosition]
//    }
//}