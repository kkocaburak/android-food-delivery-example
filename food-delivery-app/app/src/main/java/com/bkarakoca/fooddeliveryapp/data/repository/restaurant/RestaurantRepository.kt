package com.bkarakoca.fooddeliveryapp.data.repository.restaurant

import com.bkarakoca.fooddeliveryapp.data.service.local.restaurant.FavoriteRestaurantEntity
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListUIModel

interface RestaurantRepository {

    fun fetchRestaurantList(): RestaurantListUIModel

    fun fetchFavoriteRestaurantIdList(): List<FavoriteRestaurantEntity>

    fun handleRestaurantFavorite(shouldRestaurantFavorite: Boolean, restaurantName: String)

}