package com.bkarakoca.fooddeliveryapp.data.service.local.restaurant

import com.bkarakoca.fooddeliveryapp.data.model.RestaurantListResponseModel
import com.bkarakoca.fooddeliveryapp.internal.util.ResourceProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject

class RestaurantDataSource @Inject constructor(
    private val restaurantDAO: RestaurantDAO,
    private val resourceProvider: ResourceProvider
) {
    fun fetchRestaurantList(): RestaurantListResponseModel {
        var jsonString: String? = null
        try {
            jsonString = resourceProvider.getAsset("restaurant_list.json")
        } catch (ioException: IOException) {
            // TODO : catch
            println(ioException.message)
        }

        val restaurantListResponseModel = object : TypeToken<RestaurantListResponseModel>() {}.type
        return Gson().fromJson(jsonString, restaurantListResponseModel)
    }

    fun fetchFavoriteRestaurantIdList(): List<FavoriteRestaurantEntity> {
        return restaurantDAO.fetchFavoriteRestaurantIdList() ?: listOf()
    }

    fun insertFavoriteRestaurantIdList(restaurantName: String) {
        restaurantDAO.insertFavoriteRestaurant(
            FavoriteRestaurantEntity(restaurantName = restaurantName)
        )
    }

    fun deleteFavoriteRestaurantIdList(restaurantName: String) {
        restaurantDAO.deleteFavoriteRestaurant(
            FavoriteRestaurantEntity(restaurantName = restaurantName)
        )
    }
}