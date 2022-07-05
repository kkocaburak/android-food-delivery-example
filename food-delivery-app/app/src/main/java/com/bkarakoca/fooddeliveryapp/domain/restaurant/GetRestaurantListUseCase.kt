package com.bkarakoca.fooddeliveryapp.domain.restaurant

import android.content.Context
import com.bkarakoca.fooddeliveryapp.R
import com.bkarakoca.fooddeliveryapp.data.repository.restaurant.RestaurantRepository
import com.bkarakoca.fooddeliveryapp.data.service.local.restaurant.FavoriteRestaurantEntity
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListUIModel
import com.bkarakoca.fooddeliveryapp.internal.util.flow.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetRestaurantListUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : FlowUseCase<GetRestaurantListUseCase.Params, RestaurantListUIModel>() {

    data class Params(
        val context: Context
    )

    override suspend fun execute(params: Params): Flow<RestaurantListUIModel> {
        val restaurantListFlow = flowOf(restaurantRepository.fetchRestaurantList(params.context))
        val favoriteRestaurantIdListFlow = flowOf(restaurantRepository.fetchFavoriteRestaurantIdList())

        return restaurantListFlow.zip(
            favoriteRestaurantIdListFlow
        ) { restaurantListUIModel, favoriteRestaurantIdList ->
            mapFavoriteRestaurants(restaurantListUIModel, favoriteRestaurantIdList)
        }
    }

    private fun mapFavoriteRestaurants(
        restaurantListUIModel: RestaurantListUIModel,
        favoriteRestaurantIdList: List<FavoriteRestaurantEntity>
    ): RestaurantListUIModel {
        favoriteRestaurantIdList.forEach { favoriteRestaurant ->
            restaurantListUIModel.restaurantList.find { restaurantUIModel ->
                restaurantUIModel.restaurantName == favoriteRestaurant.restaurantName
            }?.apply {
                isRestaurantFavorite = true
                restaurantFavoriteIconResId = R.drawable.favorite
            }
        }
        return restaurantListUIModel
    }

}