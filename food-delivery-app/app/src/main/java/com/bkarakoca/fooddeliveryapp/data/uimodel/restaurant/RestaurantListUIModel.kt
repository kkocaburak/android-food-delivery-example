package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

data class RestaurantListUIModel(
    val restaurantItemList: List<RestaurantListItemType>,
    val favoriteRestaurantsItemList: List<RestaurantListItemType>,
    val openRestaurantsItemList: List<RestaurantListItemType>,
    val closingRestaurantsItemList: List<RestaurantListItemType>,
    val closedRestaurantsItemList: List<RestaurantListItemType>
)