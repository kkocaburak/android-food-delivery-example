package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import com.bkarakoca.fooddeliveryapp.R

enum class RestaurantImageType(val type: String, val imageResId: Int) {
    PIZZA("pizza", R.drawable.pizza_image),
    SOFT_FOOD("soft food", R.drawable.soft_food_image),
    ASIAN_FOOD("asian food", R.drawable.asian_food_image),
    DESSERT("dessert", R.drawable.dessert_image),
    RESTAURANT("restaurant", R.drawable.restaurant_image),
    NONE("", R.drawable.no_food_image);

    companion object {
        fun getImageResIdFromType(type: String): Int {
            return values().find { type == it.type }?.imageResId
                ?: NONE.imageResId
        }
    }
}