package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.ListAdapterItem
import kotlinx.parcelize.Parcelize

data class RestaurantListUIModel(
    val restaurantList: List<RestaurantUIModel>
)

@Entity(tableName = "restaurantUIModel")
@Parcelize
data class RestaurantUIModel(
    @PrimaryKey(autoGenerate = true)
    override var id: Long? = null,
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
    var isRestaurantFavorite: Boolean,
    var restaurantFavoriteIconResId: Int = R.drawable.favorite_not
) : ListAdapterItem, Parcelable {
    fun getFavoriteIcon(): Int {
        return if (isRestaurantFavorite) {
            R.drawable.favorite
        } else {
            R.drawable.favorite_not
        }
    }

    override fun equals(other: Any?): Boolean {
        return false
    }

}

fun RestaurantUIModel.handleFavorite(shouldRestaurantFavorite: Boolean) {
    isRestaurantFavorite = shouldRestaurantFavorite
    restaurantFavoriteIconResId = if (shouldRestaurantFavorite) {
        R.drawable.favorite
    } else {
        R.drawable.favorite_not
    }
}