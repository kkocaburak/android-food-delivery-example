package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.data.model.*
import com.bkarakoca.fooddeliveryapp.internal.extension.toSafeDouble
import com.bkarakoca.fooddeliveryapp.internal.extension.toSafeInt
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
                bestMatchText = item.sortingValues?.bestMatch.toSafeString(),
                bestMatchValue = item.sortingValues?.bestMatch.toSafeDouble(),
                newestText = item.sortingValues?.newest.toSafeString(),
                newestValue = item.sortingValues?.newest.toSafeDouble(),
                restaurantRatingText = item.sortingValues?.ratingAverage.toSafeString(),
                restaurantRatingValue = item.sortingValues?.ratingAverage.toSafeDouble(),
                restaurantDeliveryDurationText = mapDurationText(item.getDeliveryDurationText()),
                restaurantDeliveryDurationValue = item.sortingValues?.distance.toSafeInt(),
                restaurantPopularityText = item.sortingValues?.popularity.toSafeString(),
                restaurantPopularityValue = item.sortingValues?.popularity.toSafeDouble(),
                averageProductPriceText = item.sortingValues?.averageProductPrice.toSafeString(),
                averageProductPriceValue = item.sortingValues?.averageProductPrice.toSafeInt(),
                deliveryCostText = item.getDeliveryCostText(),
                deliveryCostValue = item.getDeliveryCostValue(),
                minimumCostText = item.getMinimumCostText(),
                minimumCostValue = item.getMinimumCostValue(),
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