package com.bkarakoca.fooddeliveryapp.scene.restaurant

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.BaseListAdapter
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.databinding.ItemRestaurantBinding
import com.bkarakoca.fooddeliveryapp.internal.extension.executeAfter
import javax.inject.Inject

class AdapterRestaurantList @Inject constructor() :
    BaseListAdapter<ItemRestaurantBinding, RestaurantUIModel>() {

    override val layoutRes get() = R.layout.item_restaurant

    override fun bind(binding: ItemRestaurantBinding, item: RestaurantUIModel) {
        binding.executeAfter {
            restaurant = item
        }
        // TODO : set onclick listener
    }
}