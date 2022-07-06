package com.bkarakoca.fooddeliveryapp.data.service.local.restaurant

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel

@Database(
    entities = [RestaurantUIModel::class],
    version = 1
)

abstract class RestaurantDataBase : RoomDatabase() {
    abstract fun restaurantDAO(): RestaurantDAO
}