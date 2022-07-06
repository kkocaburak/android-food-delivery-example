package com.bkarakoca.fooddeliveryapp.scene.restaurant.listing

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.BaseListAdapter
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.isRestaurantClosed
import com.bkarakoca.fooddeliveryapp.databinding.ItemRestaurantBinding
import com.bkarakoca.fooddeliveryapp.internal.extension.executeAfter
import javax.inject.Inject

class AdapterRestaurantList @Inject constructor() :
    BaseListAdapter<ItemRestaurantBinding, RestaurantUIModel>() {

    private var onRestaurantClick: ((RestaurantUIModel) -> Unit)? = null
    private var onRestaurantFavoriteClick: ((RestaurantUIModel) -> Unit)? = null

    override val layoutRes get() = R.layout.item_restaurant

    override fun bind(binding: ItemRestaurantBinding, item: RestaurantUIModel, index: Int) {
        binding.executeAfter {
            restaurant = item
            if (item.isRestaurantClosed()) {
                imageViewRestaurant.colorFilter =
                    ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f) })
            }
        }

        binding.cardViewRestaurantItem.setOnClickListener {
            onRestaurantClick?.invoke(item)
        }

        binding.imageViewRestaurantFavorite.setOnClickListener {
            onRestaurantFavoriteClick?.invoke(item)
        }
    }

    fun setOnRestaurantClickListener(onRestaurantClick: ((RestaurantUIModel) -> Unit)?) {
        this.onRestaurantClick = onRestaurantClick
    }

    fun setOnRestaurantFavoriteClickListener(onRestaurantFavoriteClick: ((RestaurantUIModel) -> Unit)?) {
        this.onRestaurantFavoriteClick = onRestaurantFavoriteClick
    }
}