package com.ovrbach.qapitalchallenge.features.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ovrbach.qapitalchallenge.R
import com.ovrbach.qapitalchallenge.common.entity.Goal
import com.ovrbach.qapitalchallenge.databinding.ItemGoalBinding
import com.ovrbach.qapitalchallenge.util.ItemClickListener
import com.ovrbach.qapitalchallenge.util.bind

class GoalsAdapter(val clickListener: ItemClickListener<Goal>) : ListAdapter<Goal, GoalsAdapter.Holder>(
    object : DiffUtil.ItemCallback<Goal>() {

        override fun areItemsTheSame(oldItem: Goal, newItem: Goal): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Goal, newItem: Goal): Boolean = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.bind(R.layout.item_goal))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }

    inner class Holder(private val binding: ItemGoalBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Goal) {
            //todo move to xml?
            itemView.setOnClickListener {
                clickListener.onItemClicked(item)
            }

            binding.item = item
            //todo is this the one?
            binding.executePendingBindings()
        }
    }

}
