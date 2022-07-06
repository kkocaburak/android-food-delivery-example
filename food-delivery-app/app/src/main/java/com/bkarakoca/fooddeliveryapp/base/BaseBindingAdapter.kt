package com.bkarakoca.fooddeliveryapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseBindingAdapter<T : ListAdapterItem>(
    diffCallback: DiffUtil.ItemCallback<T> = ListAdapterItemDiffCallback(),
    var currentSelected: T? = null
) : ListAdapter<T, BaseBindingViewHolder<T>>(diffCallback) {

    abstract fun getItemLayoutId(viewType: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder<T> {
        val holder = BaseBindingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), getItemLayoutId(viewType), parent, false
            ),
            currentSelected
        )
        holder.viewType = viewType
        return holder
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}