package com.bkarakoca.fooddeliveryapp.internal.extension

fun Any?.toSafeString(): String {
    return this?.toString() ?: ""
}