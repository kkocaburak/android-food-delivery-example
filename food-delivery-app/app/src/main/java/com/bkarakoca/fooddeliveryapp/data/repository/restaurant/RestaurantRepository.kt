package com.bkarakoca.fooddeliveryapp.data.repository.restaurant

import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantUIModel

interface RestaurantRepository {

    fun fetchRestaurantListFromLocal(): List<RestaurantUIModel>

    fun fetchRestaurantListFromRoom(): List<RestaurantUIModel>

    fun insertRestaurantListUIModel(restaurantUIList: List<RestaurantUIModel>)

    fun updateRestaurantFavorite(restaurantUIModel: RestaurantUIModel)

}