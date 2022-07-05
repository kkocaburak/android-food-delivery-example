package com.bkarakoca.fooddeliveryapp.data.service.local.restaurant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteRestaurant")
data class FavoriteRestaurantEntity(
    @PrimaryKey(autoGenerate = true)
    val primaryKeyId: Int,
    val restaurantName: String,
)
