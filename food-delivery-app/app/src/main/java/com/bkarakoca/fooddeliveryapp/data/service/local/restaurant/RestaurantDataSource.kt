package com.bkarakoca.fooddeliveryapp.data.service.local.restaurant

import android.content.Context
import com.bkarakoca.fooddeliveryapp.data.model.RestaurantListResponseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject

class RestaurantDataSource @Inject constructor(
    private val restaurantDAO: RestaurantDAO
) {
    fun fetchRestaurantList(context: Context): RestaurantListResponseModel {
        var jsonString: String? = null
        try {
            jsonString = context.assets.open("restaurant_list.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            // TODO : catch
            println(ioException.message)
        }

        val restaurantListResponseModel = object : TypeToken<RestaurantListResponseModel>() {}.type
        return Gson().fromJson(jsonString, restaurantListResponseModel)
    }

    fun fetchFavoriteRestaurantIdList(): List<FavoriteRestaurantEntity> {
        return restaurantDAO.fetchFavoriteRestaurantIdList()
    }
}