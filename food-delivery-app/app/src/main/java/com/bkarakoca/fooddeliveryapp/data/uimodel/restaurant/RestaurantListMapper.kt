package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.data.model.*
import com.bkarakoca.fooddeliveryapp.internal.extension.toSafeString
import com.bkarakoca.fooddeliveryapp.internal.util.ResourceProvider
import javax.inject.Inject

class RestaurantListMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapResponseToUIModel(responseModel: RestaurantListResponseModel): List<RestaurantUIModel> {
        return responseModel.restaurantList.map { item ->
            RestaurantUIModel(
                restaurantImageResId = item.getRestaurantImageResId(),
                restaurantRatingColorId = item.getRatingColor(),
                restaurantName = item.name.toSafeString(),
                restaurantStatusType = item.getRestaurantStatus(),
                bestMatch = item.sortingValues?.bestMatch.toSafeString(),
                newest = item.sortingValues?.newest.toSafeString(),
                restaurantRating = item.sortingValues?.ratingAverage.toSafeString(),
                restaurantDeliveryDuration = mapDurationText(item.getDeliveryDurationText()),
                restaurantPopularity = item.sortingValues?.popularity.toSafeString(),
                averageProductPrice = item.sortingValues?.averageProductPrice.toSafeString(),
                deliveryCost = item.getDeliveryCostText(),
                minimumCost = item.getMinimumCostText(),
                isRestaurantFavorite = false
            )
        }.filter { restaurant ->
            restaurant.restaurantName.isNotBlank()
        }
    }

    private fun mapDurationText(deliveryDurationText: String): String {
        return deliveryDurationText + resourceProvider.getString(R.string.minute_text)
    }

}