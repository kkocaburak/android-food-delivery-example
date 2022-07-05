package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.data.model.*
import com.bkarakoca.fooddeliveryapp.internal.extension.toSafeString
import com.bkarakoca.fooddeliveryapp.internal.util.ResourceProvider
import javax.inject.Inject

class RestaurantListMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapResponseToUIModel(responseModel: RestaurantListResponseModel): RestaurantListUIModel {
        return RestaurantListUIModel(
            restaurantList = responseModel.restaurantList.map {
                RestaurantUIModel(
                    restaurantImageResId = it.getRestaurantImageResId(),
                    restaurantRatingColorId = it.getRatingColor(),
                    restaurantName = it.name.toSafeString(),
                    restaurantStatusType = it.getRestaurantStatus(),
                    bestMatch = it.sortingValues?.bestMatch.toSafeString(),
                    newest = it.sortingValues?.newest.toSafeString(),
                    restaurantRating = it.sortingValues?.ratingAverage.toSafeString(),
                    restaurantDeliveryDuration = mapDurationText(it.getDeliveryDurationText()),
                    restaurantPopularity = it.sortingValues?.popularity.toSafeString(),
                    averageProductPrice = it.sortingValues?.averageProductPrice.toSafeString(),
                    deliveryCost = it.getDeliveryCostText(),
                    minimumCost = it.getMinimumCostText()
                )
            }.filter { restaurant ->
                restaurant.restaurantName.isNotBlank()
            }
        )
    }

    private fun mapDurationText(deliveryDurationText: String): String {
        return deliveryDurationText + resourceProvider.getString(R.string.minute_text)
    }

}