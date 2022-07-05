package com.bkarakoca.fooddeliveryapp.data.repository.restaurant

import android.content.Context
import com.bkarakoca.fooddeliveryapp.data.service.local.restaurant.FavoriteRestaurantEntity
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantListUIModel

interface RestaurantRepository {
    fun fetchRestaurantList(context: Context): RestaurantListUIModel
    fun fetchFavoriteRestaurantIdList(): List<FavoriteRestaurantEntity>
}