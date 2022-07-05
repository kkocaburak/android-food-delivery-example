package com.bkarakoca.fooddeliveryapp.data.service.local.restaurant

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteRestaurantEntity::class],
    version = 1
)

abstract class RestaurantDataBase : RoomDatabase() {
    abstract fun restaurantDAO(): RestaurantDAO
}