package com.bkarakoca.fooddeliveryapp.data.repository.restaurant

import com.bkarakoca.fooddeliveryapp.data.model.RestaurantListResponseModel
import com.bkarakoca.fooddeliveryapp.data.service.local.restaurant.FavoriteRestaurantEntity
import com.bkarakoca.fooddeliveryapp.data.service.local.restaurant.RestaurantDataSource
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListMapper
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListUIModel
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantDataSource: RestaurantDataSource,
    private val restaurantListMapper: RestaurantListMapper
) : RestaurantRepository {

    override fun fetchRestaurantList(): RestaurantListUIModel {
        return responseToUI(restaurantDataSource.fetchRestaurantList())
    }

    private fun responseToUI(responseModel: RestaurantListResponseModel): RestaurantListUIModel {
        return restaurantListMapper.mapResponseToUIModel(responseModel)
    }

    override fun fetchFavoriteRestaurantIdList(): List<FavoriteRestaurantEntity> {
        return restaurantDataSource.fetchFavoriteRestaurantIdList()
    }

    override fun handleRestaurantFavorite(shouldRestaurantFavorite: Boolean, restaurantName: String) {
        return if (shouldRestaurantFavorite) {
            restaurantDataSource.insertFavoriteRestaurantIdList(restaurantName)
        } else {
            restaurantDataSource.deleteFavoriteRestaurantIdList(restaurantName)
        }
    }
}