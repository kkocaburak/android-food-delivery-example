package com.bkarakoca.fooddeliveryapp.internal.extension

fun Int?.toSafeInt(): Int {
    return this ?: 0
}