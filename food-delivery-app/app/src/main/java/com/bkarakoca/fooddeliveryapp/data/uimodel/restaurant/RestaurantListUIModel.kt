package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.ListAdapterItem
import kotlinx.parcelize.Parcelize

data class RestaurantListUIModel(
    val restaurantList: List<RestaurantUIModel>
)

@Parcelize
data class RestaurantUIModel(
    val restaurantId: Long,
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
) : ListAdapterItem, Parcelable {
    override val id: String = restaurantId.toString()
}