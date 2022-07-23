package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantListItemType

data class RestaurantListUIModel(
    val restaurantItemList: List<RestaurantListItemType>,
    val favoriteRestaurantsItemList: List<RestaurantListItemType>,
    val openRestaurantsItemList: List<RestaurantListItemType>,
    val closingRestaurantsItemList: List<RestaurantListItemType>,
    val closedRestaurantsItemList: List<RestaurantListItemType>
)