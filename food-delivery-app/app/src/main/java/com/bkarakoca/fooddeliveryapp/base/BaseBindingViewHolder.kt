package com.bkarakoca.fooddeliveryapp.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bkarakoca.fooddeliveryapp.BR

class BaseBindingViewHolder<T>(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    // TODO : delete "executePendingBindings" line and retry submit list to adapter in restaurant list
    }
}