package com.bkarakoca.fooddeliveryapp.data.service.local.restaurant

import com.bkarakoca.fooddeliveryapp.data.model.RestaurantListResponseModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.internal.util.ResourceProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject

class RestaurantDataSource @Inject constructor(
    private val restaurantDAO: RestaurantDAO,
    private val resourceProvider: ResourceProvider
) {
    fun fetchRestaurantListFromLocal(): RestaurantListResponseModel {
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

    fun fetchRestaurantListFromRoom(): List<RestaurantUIModel>? {
        return restaurantDAO.fetchRestaurantListUIModel()
    }

    fun insertRestaurantListUIModel(restaurantUIList: List<RestaurantUIModel>) {
        return restaurantDAO.insertOrUpdateRestaurantUIModel(restaurantUIList)
    }

    fun updateRestaurantFavorite(restaurantUIModel: RestaurantUIModel): Boolean {
        val storedList = restaurantDAO.fetchRestaurantListUIModel()
        storedList?.find {
            it.id == restaurantUIModel.id
        }?.apply {
            isRestaurantFavorite = !restaurantUIModel.isRestaurantFavorite
        }

        return if (storedList != null) {
            restaurantDAO.updateRestaurantFavorite(storedList)
            true
        } else {
            false
        }
    }
}