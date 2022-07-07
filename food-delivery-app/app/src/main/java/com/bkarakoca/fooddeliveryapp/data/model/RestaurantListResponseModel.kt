package com.bkarakoca.fooddeliveryapp.data.model

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantImageType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantStatusType
import com.bkarakoca.fooddeliveryapp.internal.extension.toSafeString
import com.google.gson.annotations.SerializedName

data class RestaurantListResponseModel(
    @SerializedName("restaurants")
    val restaurantList: List<RestaurantModel>
)

data class RestaurantModel(
    val restaurantType: String? = null,
    val name: String? = null,
    val status: String? = null,
    val sortingValues: RestaurantSortingValues? = null,
)

fun RestaurantModel.getRestaurantImageResId(): Int {
    return restaurantType?.let { type ->
        RestaurantImageType.getImageResIdFromType(type)
    } ?: RestaurantImageType.NONE.imageResId
}

fun RestaurantModel.getRatingColor(): Int {
    return sortingValues?.ratingAverage?.let { ratingAverage ->
        when {
            ratingAverage < 1.1 -> R.color.restaurant_black_color
            ratingAverage < 2.1 -> R.color.restaurant_gray_color
            ratingAverage < 3.1 -> R.color.restaurant_orange_color
            ratingAverage < 4.1 -> R.color.restaurant_low_green_color
            ratingAverage <= 5.0 -> R.color.restaurant_green_color
            else -> R.color.restaurant_black_color
        }
    } ?: R.color.restaurant_black_color
}

fun RestaurantModel.getRestaurantStatus(): RestaurantStatusType {
    return status?.let { status ->
        RestaurantStatusType.getStatusType(status)
    } ?: RestaurantStatusType.CLOSED
}

fun RestaurantModel.getDeliveryDurationText(): String {
    return sortingValues?.distance?.let { restaurantDistance ->
        val minDurationInMinutes = 5 + restaurantDistance * 5 / 1000
        val maxDurationInMinutes = 15 + restaurantDistance * 5 / 1000
        if (maxDurationInMinutes > 60) {
            "60+"
        } else {
            "$minDurationInMinutes - $maxDurationInMinutes"
        }
    } ?: "60+"
}

fun RestaurantModel.getAverageProductPriceText(): String {
    return ((sortingValues?.averageProductPrice?.toDouble() ?: 0.0) / 100.0).toSafeString() + "€"
}

fun RestaurantModel.getDeliveryCostText(): String {
    return ((sortingValues?.deliveryCosts?.toDouble() ?: 0.0) / 100.0).toSafeString() + "€"
}

fun RestaurantModel.getDeliveryCostValue(): Double {
    return ((sortingValues?.deliveryCosts?.toDouble() ?: 0.0) / 100.0)
}

fun RestaurantModel.getMinimumCostText(): String {
    return ((sortingValues?. minCost?.toDouble() ?: 0.0) / 100.0).toSafeString() + "€"
}

fun RestaurantModel.getMinimumCostValue(): Double {
    return ((sortingValues?. minCost?.toDouble() ?: 0.0) / 100.0)
}

data class RestaurantSortingValues(
    val bestMatch: Double? = null,
    val newest: Double? = null,
    val ratingAverage: Double? = null,
    val distance: Int? = null,
    val popularity: Double? = null,
    val averageProductPrice: Int? = null,
    val deliveryCosts: Int? = null,
    val minCost: Int? = null,
)