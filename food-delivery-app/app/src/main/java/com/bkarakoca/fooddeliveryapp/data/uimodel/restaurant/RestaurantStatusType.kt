package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

enum class RestaurantStatusType(val status: String) {
    OPEN("open"), CLOSED("closed"), ORDER_AHEAD("order ahead");

    companion object {
        fun getStatusType(status: String): RestaurantStatusType {
            return values().find {
                status == it.status
            } ?: CLOSED
        }
    }
}