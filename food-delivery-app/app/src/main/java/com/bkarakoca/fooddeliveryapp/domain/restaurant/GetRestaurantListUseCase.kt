package com.bkarakoca.fooddeliveryapp.domain.restaurant

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.data.repository.restaurant.RestaurantRepository
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantHeaderUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListItemType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantStatusType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.internal.util.ResourceProvider
import com.bkarakoca.fooddeliveryapp.internal.util.flow.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetRestaurantListUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    private val resourceProvider: ResourceProvider
) : FlowUseCase<Unit, List<RestaurantListItemType>>() {

    override suspend fun execute(params: Unit): Flow<List<RestaurantListItemType>> {
        val restaurantListRoomFlow = flowOf(restaurantRepository.fetchRestaurantListFromRoom())
        val restaurantListLocalFlow = flowOf(restaurantRepository.fetchRestaurantListFromLocal())

        return restaurantListRoomFlow.zip(
            restaurantListLocalFlow
        ) { restaurantListRoom, restaurantListLocal ->
            if (restaurantListRoom?.isNullOrEmpty() != true) {
                getSectionedRestaurantList(restaurantListRoom)
            } else {
                restaurantRepository.insertRestaurantListUIModel(restaurantListLocal)
                getSectionedRestaurantList(restaurantListLocal)
            }
        }
    }

    private fun getSectionedRestaurantList(restaurantList: List<RestaurantUIModel>): List<RestaurantListItemType> {
        val restaurantItemList = arrayListOf<RestaurantListItemType>()

        val favoriteRestaurantsSection = RestaurantHeaderUIModel(
            header = resourceProvider.getString(R.string.text_restaurants_section_favorite)
        )
        val openRestaurantsSection = RestaurantHeaderUIModel(
            header = resourceProvider.getString(R.string.text_restaurants_section_open)
        )
        val closingRestaurantsSection = RestaurantHeaderUIModel(
            header = resourceProvider.getString(R.string.text_restaurants_section_order_ahead)
        )
        val closedRestaurantsSection = RestaurantHeaderUIModel(
            header = resourceProvider.getString(R.string.text_restaurants_section_closed)
        )

        val favoriteRestaurantsList = restaurantList.filter { restaurant ->
            restaurant.isRestaurantFavorite
        }
        restaurantItemList.add(favoriteRestaurantsSection)
        restaurantItemList.addAll(favoriteRestaurantsList)

        val openRestaurantsList = restaurantList.filter { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.OPEN
                    && restaurant.isRestaurantFavorite.not()
        }
        restaurantItemList.add(openRestaurantsSection)
        restaurantItemList.addAll(openRestaurantsList)

        val closingRestaurantsList = restaurantList.filter { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.ORDER_AHEAD
                    && restaurant.isRestaurantFavorite.not()
        }
        restaurantItemList.add(closingRestaurantsSection)
        restaurantItemList.addAll(closingRestaurantsList)

        val closedRestaurantsList = restaurantList.filter { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.CLOSED
                    && restaurant.isRestaurantFavorite.not()
        }
        restaurantItemList.add(closedRestaurantsSection)
        restaurantItemList.addAll(closedRestaurantsList)

        return restaurantItemList
    }

}