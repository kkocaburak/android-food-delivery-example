package com.bkarakoca.fooddeliveryapp.domain.restaurant

import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.data.repository.restaurant.RestaurantRepository
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.*
import com.bkarakoca.fooddeliveryapp.internal.util.ResourceProvider
import com.bkarakoca.fooddeliveryapp.internal.util.flow.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetRestaurantListUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    private val resourceProvider: ResourceProvider
) : FlowUseCase<Unit, RestaurantListUIModel>() {

    override suspend fun execute(params: Unit): Flow<RestaurantListUIModel> {
        val restaurantListRoomFlow = flowOf(restaurantRepository.fetchRestaurantListFromRoom())
        val restaurantListLocalFlow = flowOf(restaurantRepository.fetchRestaurantListFromLocal())

        return restaurantListRoomFlow.zip(
            restaurantListLocalFlow
        ) { restaurantListRoom, restaurantListLocal ->
            if (!restaurantListRoom.isNullOrEmpty()) {
                getSectionedRestaurantList(restaurantListRoom)
            } else {
                restaurantRepository.insertRestaurantListUIModel(restaurantListLocal)
                getSectionedRestaurantList(restaurantRepository.fetchRestaurantListFromRoom())
            }
        }
    }

    private fun getSectionedRestaurantList(restaurantList: List<RestaurantUIModel>): RestaurantListUIModel {
        val sortedRestaurantList = sortRestaurantListByStatus(restaurantList)
        val restaurantItemList = arrayListOf<RestaurantListItemType>()

        val favoriteRestaurantsItemList = getFavoriteRestaurants(sortedRestaurantList)
        val openRestaurantsItemList = getOpenRestaurants(sortedRestaurantList)
        val closingRestaurantsItemList = getClosingRestaurants(sortedRestaurantList)
        val closedRestaurantsItemList = getClosedRestaurants(sortedRestaurantList)

        restaurantItemList.addAll(favoriteRestaurantsItemList)
        restaurantItemList.addAll(openRestaurantsItemList)
        restaurantItemList.addAll(closingRestaurantsItemList)
        restaurantItemList.addAll(closedRestaurantsItemList)

        return RestaurantListUIModel(
            restaurantItemList,
            favoriteRestaurantsItemList,
            openRestaurantsItemList,
            closingRestaurantsItemList,
            closedRestaurantsItemList
        )
    }

    private fun sortRestaurantListByStatus(restaurantList: List<RestaurantUIModel>): List<RestaurantUIModel> {
        return restaurantList.sortedBy {
            it.restaurantStatusType == RestaurantStatusType.CLOSED
        }
    }

    private fun getFavoriteRestaurants(
        restaurantList: List<RestaurantUIModel>
    ): List<RestaurantListItemType> {
        val favoriteRestaurants = arrayListOf<RestaurantListItemType>()
        val favoriteRestaurantsList = restaurantList.filter { restaurant ->
            restaurant.isRestaurantFavorite
        }

        val favoriteRestaurantsSection = RestaurantHeaderUIModel(
            header = resourceProvider.getString(R.string.text_restaurants_section_favorite),
            sectionItemSize = favoriteRestaurantsList.size
        )
        favoriteRestaurants.add(favoriteRestaurantsSection)

        if (favoriteRestaurantsList.isNotEmpty()) {
            favoriteRestaurants.addAll(favoriteRestaurantsList)
        } else {
            val emptySection = RestaurantSectionEmptyUIModel(
                emptySectionText = resourceProvider.getString(R.string.text_restaurants_section_favorite_empty),
            )
            favoriteRestaurants.add(emptySection)
        }
        return favoriteRestaurants
    }

    private fun getOpenRestaurants(
        restaurantList: List<RestaurantUIModel>
    ): List<RestaurantListItemType> {
        val openRestaurants = arrayListOf<RestaurantListItemType>()
        val openRestaurantsList = restaurantList.filter { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.OPEN
                    && restaurant.isRestaurantFavorite.not()
        }
        val openRestaurantsSection = RestaurantHeaderUIModel(
            header = resourceProvider.getString(R.string.text_restaurants_section_open),
            sectionItemSize = openRestaurantsList.size
        )
        openRestaurants.add(openRestaurantsSection)
        openRestaurants.addAll(openRestaurantsList)
        return openRestaurants
    }

    private fun getClosingRestaurants(
        restaurantList: List<RestaurantUIModel>
    ): List<RestaurantListItemType> {
        val closingRestaurants = arrayListOf<RestaurantListItemType>()
        val closingRestaurantsList = restaurantList.filter { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.ORDER_AHEAD
                    && restaurant.isRestaurantFavorite.not()
        }
        val closingRestaurantsSection = RestaurantHeaderUIModel(
            header = resourceProvider.getString(R.string.text_restaurants_section_order_ahead),
            sectionItemSize = closingRestaurantsList.size
        )
        closingRestaurants.add(closingRestaurantsSection)
        closingRestaurants.addAll(closingRestaurantsList)
        return closingRestaurants
    }

    private fun getClosedRestaurants(
        restaurantList: List<RestaurantUIModel>
    ): List<RestaurantListItemType> {
        val closedRestaurants = arrayListOf<RestaurantListItemType>()
        val closedRestaurantsList = restaurantList.filter { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.CLOSED
                    && restaurant.isRestaurantFavorite.not()
        }
        val closedRestaurantsSection = RestaurantHeaderUIModel(
            header = resourceProvider.getString(R.string.text_restaurants_section_closed),
            sectionItemSize = closedRestaurantsList.size
        )
        closedRestaurants.add(closedRestaurantsSection)
        closedRestaurants.addAll(closedRestaurantsList)
        return closedRestaurants
    }

}