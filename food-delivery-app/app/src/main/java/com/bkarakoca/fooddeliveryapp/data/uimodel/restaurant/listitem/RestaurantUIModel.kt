package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantStatusType
import kotlinx.parcelize.Parcelize

@Entity(tableName = "restaurantUIModel")
@Parcelize
data class RestaurantUIModel(
    @PrimaryKey(autoGenerate = true)
    override var id: Long? = null,
    @LayoutRes override val layoutId: Int = R.layout.item_restaurant,
    @DrawableRes val restaurantImageResId: Int,
    @ColorRes val restaurantRatingColorId: Int,
    val restaurantName: String,
    val restaurantStatusType: RestaurantStatusType,
    val bestMatchText: String,
    val bestMatchValue: Double,
    val newestText: String,
    val newestValue: Double,
    val restaurantRatingText: String,
    val restaurantRatingValue: Double,
    val restaurantDeliveryDurationText: String,
    val restaurantDeliveryDurationValue: Int,
    val restaurantPopularityText: String,
    val restaurantPopularityValue: Double,
    val averageProductPriceText: String,
    val averageProductPriceValue: Int,
    val deliveryCostText: String,
    val deliveryCostValue: Double,
    val minimumCostText: String,
    val minimumCostValue: Double,
    var isRestaurantFavorite: Boolean,
    var restaurantFavoriteIconResId: Int = R.drawable.favorite_not
) : Parcelable, RestaurantListItemType {
    fun getFavoriteIcon(): Int {
        return if (isRestaurantFavorite) {
            R.drawable.favorite
        } else {
            R.drawable.favorite_not
        }
    }
}

fun RestaurantUIModel.isRestaurantClosed(): Boolean {
    return restaurantStatusType == RestaurantStatusType.CLOSED
}