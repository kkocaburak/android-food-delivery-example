package com.bkarakoca.fooddeliveryapp.domain.restaurant

import com.bkarakoca.fooddeliveryapp.data.repository.restaurant.RestaurantRepository
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.internal.util.flow.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetRestaurantListUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : FlowUseCase<Unit, RestaurantListUIModel>() {

    override suspend fun execute(params: Unit): Flow<RestaurantListUIModel> {
        val restaurantListRoomFlow = flowOf(restaurantRepository.fetchRestaurantListFromRoom())
        val restaurantListLocalFlow = flowOf(restaurantRepository.fetchRestaurantListFromLocal())

        return restaurantListRoomFlow.zip(
            restaurantListLocalFlow
        ) { restaurantListRoom, restaurantListLocal ->
            if (restaurantListRoom?.isNullOrEmpty() != true) {
                RestaurantListUIModel(sortRestaurantList(restaurantListRoom))
            } else {
                restaurantRepository.insertRestaurantListUIModel(restaurantListLocal)
                RestaurantListUIModel(sortRestaurantList(restaurantListLocal))
            }
        }
    }

    private fun sortRestaurantList(restaurantListRoom: List<RestaurantUIModel>): List<RestaurantUIModel> {
        return restaurantListRoom.sortedBy { restaurantUIModel ->
            restaurantUIModel.isRestaurantFavorite.not()
        }.sortedBy { restaurantUIModel ->
            restaurantUIModel.restaurantStatusType
        }
    }

}