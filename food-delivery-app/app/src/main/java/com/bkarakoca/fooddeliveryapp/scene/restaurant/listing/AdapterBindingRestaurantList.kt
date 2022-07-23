package com.bkarakoca.fooddeliveryapp.scene.restaurant.listing

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.BaseBindingAdapter
import com.bkarakoca.fooddeliveryapp.base.BaseBindingViewHolder
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantListItemType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.isRestaurantClosed
import com.bkarakoca.fooddeliveryapp.databinding.ItemRestaurantBinding
import javax.inject.Inject

class AdapterBindingRestaurantList @Inject constructor() : BaseBindingAdapter<RestaurantListItemType>() {

    private var onRestaurantClick: ((RestaurantUIModel) -> Unit)? = null
    private var onRestaurantFavoriteClick: ((RestaurantUIModel) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(
        holder: BaseBindingViewHolder<RestaurantListItemType>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)

        val item = getItem(position)

        when (item.layoutId) {
            R.layout.item_restaurant -> {
                (holder.binding as ItemRestaurantBinding).apply {
                    item as RestaurantUIModel
                    if (item.isRestaurantClosed()) {
                        imageViewRestaurant.colorFilter =
                            ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f) })
                    }

                    cardViewRestaurantItem.setOnClickListener {
                        onRestaurantClick?.invoke(item)
                    }
                    imageViewRestaurantFavorite.setOnClickListener {
                        onRestaurantFavoriteClick?.invoke(item)
                    }
                }
            }
        }
    }

    fun setOnRestaurantClickListener(onRestaurantClick: ((RestaurantUIModel) -> Unit)?) {
        this.onRestaurantClick = onRestaurantClick
    }

    fun setOnRestaurantFavoriteClickListener(onRestaurantFavoriteClick: ((RestaurantUIModel) -> Unit)?) {
        this.onRestaurantFavoriteClick = onRestaurantFavoriteClick
    }
}