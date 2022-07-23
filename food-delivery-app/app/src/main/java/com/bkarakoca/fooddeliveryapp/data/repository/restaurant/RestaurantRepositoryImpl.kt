package com.bkarakoca.fooddeliveryapp.data.repository.restaurant

import com.bkarakoca.fooddeliveryapp.data.service.local.restaurant.RestaurantDataSource
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListMapper
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantUIModel
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantDataSource: RestaurantDataSource,
    private val restaurantListMapper: RestaurantListMapper
) : RestaurantRepository {

    override fun fetchRestaurantListFromLocal(): List<RestaurantUIModel> {
        val responseModel = restaurantDataSource.fetchRestaurantListFromLocal()
        return restaurantListMapper.mapResponseToUIModel(responseModel)
    }

    override fun fetchRestaurantListFromRoom(): List<RestaurantUIModel> {
        return restaurantDataSource.fetchRestaurantListFromRoom()
    }

    override fun insertRestaurantListUIModel(restaurantUIList: List<RestaurantUIModel>) {
        restaurantDataSource.insertRestaurantListUIModel(restaurantUIList)
    }

    override fun updateRestaurantFavorite(restaurantUIModel: RestaurantUIModel) {
        return restaurantDataSource.updateRestaurantFavorite(restaurantUIModel)
    }
}