package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import com.bkarakoca.fooddeliveryapp.base.ListAdapterItem

sealed class RestaurantListItemType : ListAdapterItem {
    data class RestaurantModel(
        val restaurantUIModel: RestaurantUIModel,
        override var id: Long? = restaurantUIModel.id,
    ) : RestaurantListItemType()

    data class RestaurantHeaderModel(
        val restaurantHeaderUIModel: RestaurantHeaderUIModel,
        override var id: Long? = restaurantHeaderUIModel.id
    ) : RestaurantListItemType()
}