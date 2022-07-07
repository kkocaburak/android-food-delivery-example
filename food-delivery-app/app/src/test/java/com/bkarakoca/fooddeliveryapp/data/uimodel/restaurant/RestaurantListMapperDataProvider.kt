package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.data.model.*

class RestaurantListMapperDataProvider {
    fun getResponseModel(): RestaurantListResponseModel {
        return RestaurantListResponseModel(
            restaurantList = listOf(
                RestaurantModel(
                    restaurantType = "asian food",
                    name = "sushico",
                    status = "open",
                    sortingValues = RestaurantSortingValues(
                        bestMatch = 50.0,
                        newest = 100.0,
                        ratingAverage = 4.5,
                        distance = 1000,
                        popularity = 5.0,
                        averageProductPrice = 1000,
                        deliveryCosts = 1500,
                        minCost = 4500
                    )
                )
            )
        )
    }

    fun getUIModel(): List<RestaurantUIModel> {
        return listOf(
            RestaurantUIModel(
                restaurantImageResId = R.drawable.asian_food_image,
                restaurantRatingColorId = R.color.restaurant_green_color,
                restaurantName = "sushico",
                restaurantStatusType = RestaurantStatusType.OPEN,
                bestMatchText = "50.0",
                bestMatchValue = 50.0,
                newestText = "100.0",
                newestValue = 100.0,
                restaurantRatingText = "4.5",
                restaurantRatingValue = 4.5,
                restaurantDeliveryDurationText = "10 - 20 min",
                restaurantDeliveryDurationValue = 1000,
                restaurantPopularityText = "5.0",
                restaurantPopularityValue = 5.0,
                averageProductPriceText = "1€",
                averageProductPriceValue = 1000,
                deliveryCostText = "1.5€",
                deliveryCostValue = 1.5,
                minimumCostText = "4.5€",
                minimumCostValue = 4.5,
                isRestaurantFavorite = false
            )
        )
    }
}