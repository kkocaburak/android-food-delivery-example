package com.bkarakoca.fooddeliveryapp.data.service.local.restaurant

import androidx.room.*
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel

@Dao
interface RestaurantDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurantListUIModel(restaurantUIList: List<RestaurantUIModel>)

    @Update
    fun updateRestaurantListUIModel(restaurantUIList: List<RestaurantUIModel>)

    fun insertOrUpdateRestaurantUIModel(restaurantUIList: List<RestaurantUIModel>) {
        val storedList = fetchRestaurantListUIModel()
        if (storedList.isNullOrEmpty().not()) {
            updateRestaurantListUIModel(restaurantUIList)
        } else {
            insertRestaurantListUIModel(restaurantUIList)
        }
    }

    @Query("Select * from restaurantUIModel")
    fun fetchRestaurantListUIModel(): List<RestaurantUIModel>?

    @Update
    fun updateRestaurantFavorite(restaurantUIList: List<RestaurantUIModel>)

}