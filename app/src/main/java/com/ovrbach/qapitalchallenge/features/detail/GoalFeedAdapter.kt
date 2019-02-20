package com.ovrbach.qapitalchallenge.features.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ovrbach.qapitalchallenge.R
import com.ovrbach.qapitalchallenge.data.entity.Feed
import com.ovrbach.qapitalchallenge.databinding.ItemFeedBinding
import com.ovrbach.qapitalchallenge.util.bind

class GoalFeedAdapter : ListAdapter<Feed, GoalFeedAdapter.Holder>(
    object : DiffUtil.ItemCallback<Feed>() {
        override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean = oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.bind(R.layout.item_feed))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }

    class Holder(private val binding: ItemFeedBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Feed) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}