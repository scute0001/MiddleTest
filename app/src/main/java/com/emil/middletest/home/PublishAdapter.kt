package com.emil.middletest.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emil.middletest.database.PublishData
import com.emil.middletest.databinding.ListPublishBinding

class PublishAdapter(val viewModel: HomeViewModel): ListAdapter<PublishData, PublishViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublishViewHolder {
        return PublishViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PublishViewHolder, position: Int) {
        holder.bind(viewModel, getItem(position))
    }
}

class PublishViewHolder(val binding: ListPublishBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(viewModel: HomeViewModel, item: PublishData) {
        binding.publishData = item

    }
    companion object {
        fun from(parent: ViewGroup): PublishViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListPublishBinding.inflate(inflater, parent, false)
            return PublishViewHolder(binding)
        }
    }

}


class DiffCallback: DiffUtil.ItemCallback<PublishData>() {
    override fun areItemsTheSame(oldItem: PublishData, newItem: PublishData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PublishData, newItem: PublishData): Boolean {
        return oldItem == newItem
    }
}