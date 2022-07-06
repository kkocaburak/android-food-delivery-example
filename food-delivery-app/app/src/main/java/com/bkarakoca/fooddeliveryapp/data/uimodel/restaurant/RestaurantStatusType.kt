package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

enum class RestaurantStatusType(val status: String) {
    OPEN("open"), ORDER_AHEAD("order ahead"), CLOSED("closed");

    companion object {
        fun getStatusType(status: String): RestaurantStatusType {
            return values().find {
                status == it.status
            } ?: CLOSED
        }
    }
}