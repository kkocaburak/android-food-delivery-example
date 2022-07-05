package com.bkarakoca.fooddeliveryapp.data.service.local.restaurant

import androidx.room.*

@Dao
interface RestaurantDAO {

    @Insert
    fun insertFavoriteRestaurant(restaurantEntity: FavoriteRestaurantEntity)

    @Delete
    fun deleteFavoriteRestaurant(restaurantEntity: FavoriteRestaurantEntity)

    @Query("Select * from favoriteRestaurant")
    fun fetchFavoriteRestaurantIdList(): List<FavoriteRestaurantEntity> = listOf()

}