package com.bkarakoca.fooddeliveryapp.domain.restaurant

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantStatusType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel

class SortRestaurantsUseCaseDataProvider {
    fun getRestaurantList(): List<RestaurantUIModel> {
        return listOf(
            RestaurantUIModel(
                id = 1L,
                layoutId = -1,
                restaurantImageResId = R.drawable.restaurant_image,
                restaurantRatingColorId = R.color.restaurant_black_color,
                restaurantName = "restaurant",
                restaurantStatusType = RestaurantStatusType.CLOSED,
                bestMatchText = "1.0",
                bestMatchValue = 1.0,
                newestText = "1.0",
                newestValue = 1.0,
                restaurantRatingText = "1.0",
                restaurantRatingValue = 1.0,
                restaurantDeliveryDurationText = "15-20 min",
                restaurantDeliveryDurationValue = 30,
                restaurantPopularityText = "1.0",
                restaurantPopularityValue = 1.0,
                averageProductPriceText = "10",
                averageProductPriceValue = 10,
                deliveryCostText = "1.5",
                deliveryCostValue = 1.5,
                minimumCostText = "3.5",
                minimumCostValue = 3.5,
                isRestaurantFavorite = true,
            ),
            RestaurantUIModel(
                id = 2L,
                layoutId = -2,
                restaurantImageResId = R.drawable.restaurant_image,
                restaurantRatingColorId = R.color.restaurant_black_color,
                restaurantName = "restaurant2",
                restaurantStatusType = RestaurantStatusType.OPEN,
                bestMatchText = "2.0",
                bestMatchValue = 2.0,
                newestText = "2.0",
                newestValue = 2.0,
                restaurantRatingText = "2.0",
                restaurantRatingValue = 2.0,
                restaurantDeliveryDurationText = "35-50 min",
                restaurantDeliveryDurationValue = 50,
                restaurantPopularityText = "2.0",
                restaurantPopularityValue = 2.0,
                averageProductPriceText = "20",
                averageProductPriceValue = 20,
                deliveryCostText = "2.5",
                deliveryCostValue = 2.5,
                minimumCostText = "5.5",
                minimumCostValue = 5.5,
                isRestaurantFavorite = true,
            ),
            RestaurantUIModel(
                id = 3L,
                layoutId = -3,
                restaurantImageResId = R.drawable.restaurant_image,
                restaurantRatingColorId = R.color.restaurant_black_color,
                restaurantName = "restaurant3",
                restaurantStatusType = RestaurantStatusType.OPEN,
                bestMatchText = "2.0",
                bestMatchValue = 2.0,
                newestText = "2.0",
                newestValue = 2.0,
                restaurantRatingText = "2.0",
                restaurantRatingValue = 2.0,
                restaurantDeliveryDurationText = "35-50 min",
                restaurantDeliveryDurationValue = 50,
                restaurantPopularityText = "2.0",
                restaurantPopularityValue = 2.0,
                averageProductPriceText = "20",
                averageProductPriceValue = 20,
                deliveryCostText = "2.5",
                deliveryCostValue = 2.5,
                minimumCostText = "5.5",
                minimumCostValue = 5.5,
                isRestaurantFavorite = true,
            )
        )
    }
}