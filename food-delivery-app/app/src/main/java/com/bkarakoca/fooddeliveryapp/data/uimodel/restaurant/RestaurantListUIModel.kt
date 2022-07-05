package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.ListAdapterItem

data class RestaurantListUIModel(
    val restaurantList: List<RestaurantUIModel>
)

data class RestaurantUIModel(
    @DrawableRes val restaurantImageResId: Int,
    @ColorRes val restaurantRatingColorId: Int,
    val restaurantName: String,
    val restaurantStatusType: RestaurantStatusType,
    val bestMatch: String,
    val newest: String,
    val restaurantRating: String,
    val restaurantDeliveryDuration: String,
    val restaurantPopularity: String,
    val averageProductPrice: String,
    val deliveryCost: String,
    val minimumCost: String,
    var isRestaurantFavorite: Boolean = false,
    var restaurantFavoriteIconResId: Int = R.drawable.favorite_not,
) : ListAdapterItem {
    override var id: String? = null
}