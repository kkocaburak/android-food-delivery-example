package com.bkarakoca.fooddeliveryapp.scene.restaurant.listing

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantHeaderUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantStatusType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel

class FRRestaurantListVMDataProvider {

    fun getRestaurantUIModel(): RestaurantUIModel {
        return RestaurantUIModel(
            -1,
            -1,
            R.drawable.restaurant_image,
            R.color.restaurant_black_color,
            "restaurant",
            RestaurantStatusType.OPEN,
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
        )
    }

    fun getRestaurantList(): RestaurantListUIModel {
        return RestaurantListUIModel(
            restaurantItemList = listOf(
                RestaurantHeaderUIModel(
                    id = 0L,
                    layoutId = -1,
                    header = "asd",
                    sectionItemSize = 3
                ),
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
                    2L,
                    -1,
                    R.drawable.restaurant_image,
                    R.color.restaurant_black_color,
                    "restaurant",
                    RestaurantStatusType.OPEN,
                    bestMatchText = "2.0",
                    bestMatchValue = 2.0,
                    newestText = "2.0",
                    newestValue = 2.0,
                    restaurantRatingText = "2.0",
                    restaurantRatingValue = 2.0,
                    restaurantDeliveryDurationText = "15-20 min",
                    restaurantDeliveryDurationValue = 50,
                    restaurantPopularityText = "2.0",
                    restaurantPopularityValue = 2.0,
                    averageProductPriceText = "20",
                    averageProductPriceValue = 20,
                    deliveryCostText = "2.5",
                    deliveryCostValue = 2.5,
                    minimumCostText = "5.5",
                    minimumCostValue = 5.5,
                    isRestaurantFavorite = false,
                ),
            ),
            favoriteRestaurantsItemList = listOf(),
            openRestaurantsItemList = listOf(),
            closingRestaurantsItemList = listOf(),
            closedRestaurantsItemList = listOf()
        )
    }

    fun getSortedListByDuration(): RestaurantListUIModel {
        return RestaurantListUIModel(
            restaurantItemList = listOf(
                RestaurantHeaderUIModel(
                    id = 0L,
                    layoutId = -1,
                    header = "asd",
                    sectionItemSize = 3
                ),
                RestaurantUIModel(
                    2L,
                    -1,
                    R.drawable.restaurant_image,
                    R.color.restaurant_black_color,
                    "restaurant2",
                    RestaurantStatusType.OPEN,
                    bestMatchText = "2.0",
                    bestMatchValue = 2.0,
                    newestText = "2.0",
                    newestValue = 2.0,
                    restaurantRatingText = "2.0",
                    restaurantRatingValue = 2.0,
                    restaurantDeliveryDurationText = "15-20 min",
                    restaurantDeliveryDurationValue = 20,
                    restaurantPopularityText = "2.0",
                    restaurantPopularityValue = 2.0,
                    averageProductPriceText = "20",
                    averageProductPriceValue = 20,
                    deliveryCostText = "2.5",
                    deliveryCostValue = 2.5,
                    minimumCostText = "5.5",
                    minimumCostValue = 5.5,
                    isRestaurantFavorite = false,
                ),
                RestaurantUIModel(
                    id = 1L,
                    layoutId = -1,
                    restaurantImageResId = R.drawable.restaurant_image,
                    restaurantRatingColorId = R.color.restaurant_black_color,
                    restaurantName = "restaurant1",
                    restaurantStatusType = RestaurantStatusType.CLOSED,
                    bestMatchText = "1.0",
                    bestMatchValue = 1.0,
                    newestText = "1.0",
                    newestValue = 1.0,
                    restaurantRatingText = "1.0",
                    restaurantRatingValue = 1.0,
                    restaurantDeliveryDurationText = "35-50 min",
                    restaurantDeliveryDurationValue = 50,
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
            ),
            favoriteRestaurantsItemList = listOf(),
            openRestaurantsItemList = listOf(),
            closingRestaurantsItemList = listOf(),
            closedRestaurantsItemList = listOf()
        )
    }

}