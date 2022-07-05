package com.bkarakoca.fooddeliveryapp.scene.restaurant.listing

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.BaseListAdapter
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.databinding.ItemRestaurantBinding
import com.bkarakoca.fooddeliveryapp.internal.extension.executeAfter
import javax.inject.Inject

class AdapterRestaurantList @Inject constructor() :
    BaseListAdapter<ItemRestaurantBinding, RestaurantUIModel>() {

    var onRestaurantClick: ((RestaurantUIModel) -> Unit)? = null
    var onRestaurantFavoriteClick: ((Boolean, String) -> Unit)? = null

    override val layoutRes get() = R.layout.item_restaurant

    override fun bind(binding: ItemRestaurantBinding, item: RestaurantUIModel, index: Int) {
        binding.executeAfter {
            restaurant = item
        }

        binding.cardViewRestaurantItem.setOnClickListener {
            onRestaurantClick?.invoke(item)
        }

        binding.imageViewRestaurantFavorite.setOnClickListener {
            onRestaurantFavoriteClick?.invoke(item.isRestaurantFavorite.not(), item.restaurantName)
        }
    }

    fun setOnRestaurantClickListener(onRestaurantClick: ((RestaurantUIModel) -> Unit)?) {
        this.onRestaurantClick = onRestaurantClick
    }

    fun setOnRestaurantFavoriteClickListener(onRestaurantFavoriteClick: ((Boolean, String) -> Unit)?) {
        this.onRestaurantFavoriteClick = onRestaurantFavoriteClick
    }

    fun setRestaurantFavoriteStatus(isFavorite: Boolean) {
//        if (isFavorite) {
//            binding.imageViewRestaurantFavorite.setImageResource(R.drawable.favorite_not)
//        } else {
//            binding.imageViewRestaurantFavorite.setImageResource(R.drawable.favorite)
//        }
    }
}