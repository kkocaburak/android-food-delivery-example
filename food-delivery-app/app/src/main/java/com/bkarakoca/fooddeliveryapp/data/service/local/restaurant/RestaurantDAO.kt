package com.bkarakoca.fooddeliveryapp.data.service.local.restaurant

import androidx.room.*

@Dao
interface RestaurantDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteRestaurant(restaurantEntity: FavoriteRestaurantEntity): Long

    @Delete
    fun deleteFavoriteRestaurant(restaurantEntity: FavoriteRestaurantEntity)

    @Query("Select * from favoriteRestaurant")
    fun fetchFavoriteRestaurantIdList(): List<FavoriteRestaurantEntity>?

}