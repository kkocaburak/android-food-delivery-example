package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.base.ListAdapterItem
import kotlinx.parcelize.Parcelize

interface RestaurantListItemType : ListAdapterItem

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

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + layoutId
        result = 31 * result + restaurantImageResId
        result = 31 * result + restaurantRatingColorId
        result = 31 * result + restaurantName.hashCode()
        result = 31 * result + restaurantStatusType.hashCode()
        result = 31 * result + bestMatchText.hashCode()
        result = 31 * result + bestMatchValue.hashCode()
        result = 31 * result + newestText.hashCode()
        result = 31 * result + newestValue.hashCode()
        result = 31 * result + restaurantRatingText.hashCode()
        result = 31 * result + restaurantRatingValue.hashCode()
        result = 31 * result + restaurantDeliveryDurationText.hashCode()
        result = 31 * result + restaurantDeliveryDurationValue
        result = 31 * result + restaurantPopularityText.hashCode()
        result = 31 * result + restaurantPopularityValue.hashCode()
        result = 31 * result + averageProductPriceText.hashCode()
        result = 31 * result + averageProductPriceValue
        result = 31 * result + deliveryCostText.hashCode()
        result = 31 * result + deliveryCostValue.hashCode()
        result = 31 * result + minimumCostText.hashCode()
        result = 31 * result + minimumCostValue.hashCode()
        result = 31 * result + isRestaurantFavorite.hashCode()
        result = 31 * result + restaurantFavoriteIconResId
        return result
    }
}

@Parcelize
data class RestaurantHeaderUIModel(
    override var id: Long? = null,
    @LayoutRes override val layoutId: Int = R.layout.item_restaurant_header,
    val header: String,
    val sectionItemSize: Int
) : ListAdapterItem, Parcelable, RestaurantListItemType

@Parcelize
data class RestaurantSectionEmptyUIModel(
    override var id: Long? = null,
    @LayoutRes override val layoutId: Int = R.layout.item_restaurant_section_empty,
    val emptySectionText: String
) : ListAdapterItem, Parcelable, RestaurantListItemType

fun RestaurantUIModel.isRestaurantClosed(): Boolean {
    return restaurantStatusType == RestaurantStatusType.CLOSED
}

fun RestaurantUIModel.handleFavorite(shouldRestaurantFavorite: Boolean) {
    isRestaurantFavorite = shouldRestaurantFavorite
    restaurantFavoriteIconResId = if (shouldRestaurantFavorite) {
        R.drawable.favorite
    } else {
        R.drawable.favorite_not
    }
}