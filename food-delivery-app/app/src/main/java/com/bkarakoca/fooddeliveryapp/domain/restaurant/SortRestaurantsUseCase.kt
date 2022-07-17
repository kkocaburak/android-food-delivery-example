package com.bkarakoca.fooddeliveryapp.domain.restaurant

import androidx.annotation.VisibleForTesting
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.*
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantSortingType.*
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantHeaderUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantListItemType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantSectionEmptyUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.internal.util.flow.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SortRestaurantsUseCase @Inject constructor() :
    FlowUseCase<SortRestaurantsUseCase.Params, RestaurantListUIModel>() {

    data class Params(
        val restaurantList: RestaurantListUIModel,
        val sortingType: RestaurantSortingType
    )

    override suspend fun execute(params: Params): Flow<RestaurantListUIModel> {
        val restaurantItemList = arrayListOf<RestaurantListItemType>()
        val favoriteRestaurants = sortList(params.restaurantList.favoriteRestaurantsItemList, params.sortingType)
        val openRestaurants = sortList(params.restaurantList.openRestaurantsItemList, params.sortingType)
        val closingRestaurants = sortList(params.restaurantList.closingRestaurantsItemList, params.sortingType)
        val closedRestaurants = sortList(params.restaurantList.closedRestaurantsItemList, params.sortingType)
        restaurantItemList.addAll(favoriteRestaurants)
        restaurantItemList.addAll(openRestaurants)
        restaurantItemList.addAll(closingRestaurants)
        restaurantItemList.addAll(closedRestaurants)

        return flowOf(
            RestaurantListUIModel(
                restaurantItemList,
                favoriteRestaurants,
                openRestaurants,
                closingRestaurants,
                closedRestaurants
            )
        )
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun sortList(
        itemList: List<RestaurantListItemType>,
        sortingType: RestaurantSortingType
    ): List<RestaurantListItemType> {
        val headerSection = itemList.find { it is RestaurantHeaderUIModel }
        val emptySection = itemList.find {it is RestaurantSectionEmptyUIModel }
        val sortedList: ArrayList<RestaurantListItemType> = ArrayList(itemList.filterIsInstance<RestaurantUIModel>().let {
            when (sortingType) {
                BEST_MATCH -> {
                    it.sortedByDescending { restaurant ->
                        restaurant.bestMatchValue
                    }
                }
                NEWEST -> {
                    it.sortedByDescending { restaurant ->
                        restaurant.newestValue
                    }
                }
                RATING -> {
                    it.sortedByDescending { restaurant ->
                        restaurant.restaurantRatingValue
                    }
                }
                DISTANCE -> {
                    it.sortedBy { restaurant ->
                        restaurant.restaurantDeliveryDurationValue
                    }
                }
                POPULARITY -> {
                    it.sortedByDescending { restaurant ->
                        restaurant.restaurantPopularityValue
                    }
                }
                AVERAGE_PRODUCT_PRICE -> {
                    it.sortedBy { restaurant ->
                        restaurant.averageProductPriceValue
                    }
                }
                DELIVERY_COST -> {
                    it.sortedBy { restaurant ->
                        restaurant.deliveryCostValue
                    }
                }
                MIN_COST -> {
                    it.sortedBy { restaurant ->
                        restaurant.minimumCostValue
                    }
                }
            }.sortedBy { restaurant ->
                restaurant.restaurantStatusType == RestaurantStatusType.CLOSED
            }
        })

        emptySection?.let {
            sortedList.add(0, it)
        }
        headerSection?.let {
            sortedList.add(0, it)
        }
        return sortedList
    }
}