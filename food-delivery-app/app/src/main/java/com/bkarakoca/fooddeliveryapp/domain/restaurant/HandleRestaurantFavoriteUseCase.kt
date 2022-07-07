package com.bkarakoca.fooddeliveryapp.domain.restaurant

import com.bkarakoca.fooddeliveryapp.data.repository.restaurant.RestaurantRepository
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.internal.util.flow.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class HandleRestaurantFavoriteUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : FlowUseCase<HandleRestaurantFavoriteUseCase.Params, Unit>() {

    data class Params(
        val restaurantUIModel: RestaurantUIModel
    )

    override suspend fun execute(params: Params): Flow<Unit> {
        return flowOf(
            restaurantRepository.updateRestaurantFavorite(params.restaurantUIModel)
        )
    }
}